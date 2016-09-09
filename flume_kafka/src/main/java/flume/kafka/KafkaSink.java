package flume.kafka;

import java.util.Map.Entry;
import java.util.Properties;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/*
 * flume sink
 * kafka producer
 */

public class KafkaSink extends AbstractSink implements Configurable {  
	
	private Properties props;  
	private Producer<String, String> producer;  
	
	private static final String CUSTOME_PARTITION_KEY = "custom.partition.key";  
	private static final String CUSTOME_TOPIC_NAME = "custom.topic.name";  
	private static final String DEFAULT_ENCODING = "UTF-8";  
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaSink.class);
	
	//从context（flume配置文件flume-conf.properties）中获取配置参数
	public void configure(Context context) {  
		props = new Properties(); 
		
		ImmutableMap<String, String> parameters = context.getParameters(); 
		for( Entry<String, String> param : parameters.entrySet() ) {
			props.put(param.getKey(), param.getValue());
		}		
				
		/**
		 * props.put("metadata.broker.list","192.168.10.100:9092");
		 * props.put("serializer.class","kafka.serializer.StringEncoder ");
		 * props.put("request.required.acks","1");
		 * props.put("custom.topic.name","mykafka");
		 * 也可以读取classpath配置文件
		 * InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("flume-config.properties");
		 * props.load(input);
		 */	
  
	}  
	
	@Override  
    public synchronized void start() {  
        super.start();  
		ProducerConfig config = new ProducerConfig(props);  
		producer = new Producer<String, String>(config);
    }
	
	public Status process() {  
		Status status = null;  
		Channel channel = getChannel();  
		Transaction tx = channel.getTransaction();  
		
		try {  
			tx.begin();  
			Event event = channel.take();  
			if (event != null) {   
				
				String partitionKey = (String) props.get(CUSTOME_PARTITION_KEY);  
				String topic = Preconditions.checkNotNull((String) props.get(CUSTOME_TOPIC_NAME),"topic name is required");  
				
				String eventData = new String(event.getBody(), DEFAULT_ENCODING);  
				logger.info("Send Message : [" + topic + ":" + eventData + "]");
				KeyedMessage<String,String> data = ( partitionKey == null || partitionKey.isEmpty() ) ? 
						new KeyedMessage<String, String>(topic, eventData) : new KeyedMessage<String, String>(topic, partitionKey, eventData); 
				producer.send(data);  
				tx.commit();    
				status = Status.READY; 
				logger.info("Send message to kafka success!");  
			} else {  
				tx.rollback();  
				status = Status.BACKOFF;  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
			tx.rollback();  
			status = Status.BACKOFF;
			logger.info("Send message to kafka failed!");  
		} finally {  
			tx.close();  
		}  
		return status;  
	}  
	
	@Override  
	public void stop() { 
		producer.close();  
		super.stop();
	}  

}  

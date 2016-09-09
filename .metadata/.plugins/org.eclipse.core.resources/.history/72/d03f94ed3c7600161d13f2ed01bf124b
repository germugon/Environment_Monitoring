package kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerTest {
	
	private String topic = null;  
	private Producer<String, String> producer = null;  
	
	public final static String DEFAULT_TOPIC = "mykafka";
	public final static String METADATA_BROKER_LIST = "192.168.10.100:9092";
	public final static String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
	public final static String REQUEST_REQUIRED_ACKS = "1";
	
	public ProducerTest( String topic ) {  
		this.topic = topic;
		
		Properties props = new Properties();  
		props.setProperty("metadata.broker.list", METADATA_BROKER_LIST);  
		props.setProperty("serializer.class", SERIALIZER_CLASS);  
		props.put("request.required.acks", REQUEST_REQUIRED_ACKS);   
		
		ProducerConfig config = new ProducerConfig(props);  
		producer = new Producer<String, String>(config);   
	}
	
	public void sendMessage( String message ) {  
		KeyedMessage<String, String> data = new KeyedMessage<String, String>( topic, message );
		
		try {  
			producer.send(data);  
			System.out.println( "Send message: " + message );  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		producer.close();
	}
}

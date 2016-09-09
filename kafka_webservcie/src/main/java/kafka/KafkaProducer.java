package kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import util.ConfigUtil;

public class KafkaProducer {
	
	private String topic = null;  
	private Producer<String, String> producer = null;  
	
	public KafkaProducer( String topic ) {  
		this.topic = topic;
		
		Properties props = new Properties();  
		props.setProperty("metadata.broker.list", ConfigUtil.METADATA_BROKER_LIST);  
		props.setProperty("serializer.class", ConfigUtil.SERIALIZER_CLASS);  
		props.put("request.required.acks", ConfigUtil.REQUEST_REQUIRED_ACKS);   
		
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

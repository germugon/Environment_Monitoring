package service;

import kafka.KafkaProducer;

public class ProducerService {
	
	public String send( String topic, String message ) {   
		KafkaProducer producer = new KafkaProducer(topic);
		producer.sendMessage(message); 
		
		return "Send";
	}  

}

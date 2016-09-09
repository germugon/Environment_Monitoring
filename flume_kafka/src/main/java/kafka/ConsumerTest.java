package kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.producer.ProducerConfig;

public class ConsumerTest extends Thread { 
	
	private String topic = null; 
	private int thread_num;
	private ConsumerConnector consumer = null;   
	
	public ConsumerTest(String topic, int thread_num) {  
		this.topic = topic;
		this.thread_num = thread_num;
		
		Properties props = new Properties();   
		props.put("zookeeper.connect","192.168.10.100:2181");   
		props.put("group.id", "0");   
		props.put("zookeeper.session.timeout.ms","10000");   
		//       props.put("zookeeper.sync.time.ms", "200");   
		//       props.put("auto.commit.interval.ms", "1000");   

		ConsumerConfig config = new ConsumerConfig(props); 
		this.consumer = Consumer.createJavaConsumerConnector(config);
		
	}     

	public void run(){   

		Map<String,Integer> topickMap = new HashMap<String, Integer>();  
		topickMap.put(topic,thread_num);	//几个线程读取topic
		
		Map<String, List<KafkaStream<byte[],byte[]>>> streamMap = consumer.createMessageStreams(topickMap);  
		KafkaStream<byte[],byte[]> stream = streamMap.get(topic).get(0);	//每个线程对应一个KafkaStream

		ConsumerIterator<byte[],byte[]> it = stream.iterator();
		while(it.hasNext()){  
			System.out.println( "Receive message: " + new String(it.next().message()) );  
		}  

	}   
	
	public static void main(String[] args) { 
		ConsumerTest consumerThread = new ConsumerTest("mykafka",2);   
		consumerThread.start();   
	} 
	
}  

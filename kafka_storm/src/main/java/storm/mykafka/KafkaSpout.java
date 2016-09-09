package storm.mykafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import util.RedisUtil;

/*
 * kafka consumer
 * storm spout
 */
public class KafkaSpout implements IRichSpout {
	private SpoutOutputCollector collector;
	
	private String topic; 
	private Properties props;  
	private ConsumerConnector consumer;   
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaSpout.class);

	public KafkaSpout(String topic) {
		this.topic = topic;
	}
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {  
		props = new Properties();  
		props.put("zookeeper.connect","192.168.10.100:2181");  
		props.put("group.id","0");  
		props.put("zookeeper.session.timeout.ms","10000");  
		//props.put("zookeeper.sync.time.ms", "200");  
		//props.put("auto.commit.interval.ms", "1000");

		this.collector = collector;  
	}  
	
	public void nextTuple() {  

	} 
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {  
		declarer.declare(new Fields("KafkaSpout"));  
	} 
	
	public void activate() {  
		ConsumerConfig config = new ConsumerConfig(props);
		consumer = Consumer.createJavaConsumerConnector(config);  
		
		Map<String, Integer> topickMap = new HashMap<String,Integer>();  
		topickMap.put(topic,new Integer(1));					//几个线程读取topic  
		
		Map<String, List<KafkaStream<byte[],byte[]>>> streamMap = consumer.createMessageStreams(topickMap);  
		KafkaStream<byte[],byte[]> stream = streamMap.get(topic).get(0);  
		
		ConsumerIterator<byte[],byte[]> it = stream.iterator();  
		while (it.hasNext()) {  
			String message = new String(it.next().message());  
			logger.debug("Receive message: " + message);  
			collector.emit(new Values(message), message);		//message:作为参数(mgdId)传递给ack和fail
		}  
	}  

	public void deactivate() {  
		// TODOAuto-generated method stub  
		
	}  

	public void ack(Object msgId) {
		// TODOAuto-generated method stub  
		System.out.println("ACK:" + msgId);
	}
	
	public void fail(Object msgId) {  
		// TODOAuto-generated method stub  
		System.out.println("FAIL:" + msgId);
	}  
	
	public void close() {
		// TODO Auto-generated method stub
		
	} 
	
	public Map<String, Object> getComponentConfiguration() {  
		// TODOAuto-generated method stub  
		return null;  
	} 

}  




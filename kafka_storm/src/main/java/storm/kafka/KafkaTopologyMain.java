package storm.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import storm.util.ConfigUtils;
import storm.util.MessageScheme;

public class KafkaTopologyMain {  

	private static final Logger logger = LoggerFactory.getLogger(KafkaTopologyMain.class);
	
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {  
		//定义拓扑 
		TopologyBuilder builder = new TopologyBuilder();           
		builder.setSpout("kafka-spout",new KafkaSpout(new MessageScheme("data")));  
		builder.setBolt("normalize-blot",new NormalizeBlot()).shuffleGrouping("kafka-spout");  
		/**
		 * 此处fieldsGrouping无意义
		 */
		builder.setBolt("calculate-blot",new CalculateBlot(),2).fieldsGrouping("normalize-blot",new Fields("device"));
		
		//配置		
		Config config = new Config();  
		config.put(ConfigUtils.CONFIG_FILE,"kafka-config.properties");
//		config.put(ConfigUtils.CONFIG_GROUP,"kafka_spout");
		config.put(ConfigUtils.CONFIG_FAIL_HANDLER,"unreliable");
		config.put(ConfigUtils.CONFIG_TOPIC,"mykafka");
		config.put(ConfigUtils.CONFIG_BUFFER_MAX_MESSAGES,"1024");
		/* 未指定config的ConfigUtils.CONFIG_FILE
		//未设置，默认为Storm的zookeeper配置
		config.put("kafka.zookeeper.connect","192.168.10.100:2181,192.168.10.101:2181,192.168.10.102:2181");
		//未设置，默认为ConfigUtils.DEFAULT_GROUP:"kafka_spout"
		config.put("kafka.group.id","kafka_spout");
		config.put("kafka.auto.commit.enable","false");
		config.put("kafka.consumer.timeout.ms","1000");
		*/
		config.setDebug(true); 
		
		//运行拓扑
		logger.debug("-------------------------------KafkaTopologyMain-----------------------------------");
//		LocalCluster cluster = new LocalCluster();  
//		cluster.submitTopology("KafkaTopology", config, builder.createTopology());
//		try {
//			Thread.sleep(300000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		cluster.killTopology("KafkaTopology");  
//		cluster.shutdown();
		StormSubmitter.submitTopology("KafkaTopology", config, builder.createTopology());  
	}  
}  

package storm.kafka;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;
import util.ConfigUtil;
import util.DateUtil;
import util.RedisUtil;

public class NormalizeBlot implements IRichBolt{  

	private static final long serialVersionUID = 1L;
	private Jedis jedis;
	private OutputCollector collector;  
	
	private static final Logger logger = LoggerFactory.getLogger(NormalizeBlot.class);
	private static final Logger originalfile_logger = LoggerFactory.getLogger("originaldata_logfile");
	private static final Logger normalizedfile_logger = LoggerFactory.getLogger("normalizeddata_logfile");

	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {  
		jedis = RedisUtil.getJedis();
		this.collector = collector;  

	}  

	public void execute(Tuple input) {  
		//input格式：device:flag,datetime,value1,flag,value2,flag...
		String message = input.getString(0); 
		String[] splits = message.split("::");
		
		originalfile_logger.info(message);
		
		if( splits.length == 2 ) {
			
			String device = splits[0];
			String record = splits[1];
			String[] data = record.split(",");
			String time = null;		
			
			try {
				time = DateUtil.date2String(DateUtil.string2Date2(data[1]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (time != null) {
				
				int pos = device.indexOf("-");
				String dev = ( pos == -1 ) ? device : device.substring(0, pos);
				
				if( dev.equals(ConfigUtil.STATION0) ) {
					for( int i = 3; i < data.length; i += 2 ){ 
						String name = data[i-1];
						String value = data[i];
						
						List<Tuple> list = new ArrayList<Tuple>();  
						list.add(input); 
						
						//数据记录到日志文件
						normalizedfile_logger.info(device + "," + name + "," + time + "," + value);
						
						//数据存入redis
						jedis.hset( device + "-" + name, time, value);
						
						//emit(tuple):断开后面bolt的ack/fail对spout的影响(unanchoring)，spout根据当前bolt调用ack/fail
						//emit(oldTuple,newTuple):后面bolt的ack/fail影响spout调用ack/fail(anchoring)
						collector.emit(list, new Values(device,name,time,value));
						
					}  
					collector.ack(input);  
					
				}
				else {
					for( int i = 2; i < data.length; i += 2 ){ 
						//if( data[i+1].equals(ConfigUtils.DATA_VALID_FLAG) )
						String name = ConfigUtil.DATA_FORMAT.get(dev).get(i/2);
						String value = data[i];
						
						List<Tuple> list = new ArrayList<Tuple>();  
						list.add(input); 
						
						//数据记录到日志文件
						normalizedfile_logger.info(device + "," + name + "," + time + "," + value);
						
						//数据存入redis
						jedis.hset( device + "-" + name, time, value);
						
						//emit(tuple):断开后面bolt的ack/fail对spout的影响(unanchoring)，spout根据当前bolt调用ack/fail
						//emit(oldTuple,newTuple):后面bolt的ack/fail影响spout调用ack/fail(anchoring)
						collector.emit(list, new Values(device,name,time,value));
						
					}  
					collector.ack(input);  
					
				}
				
			} 
			else {
				logger.error("数据格式 device:flag,datetime,value1,flag,value2,flag...\n" 
						+ "或者 device:DataTime,datetime,name1,value1,name2,value2...");
//				collector.fail(input);
			}
			
		}
		else {
			logger.error("数据日期时间格式为29/02/2016 23:45:00");
//			collector.fail(input);
		}
	}  
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {  
		declarer.declare(new Fields("device","name","time","value"));  

	}  

	public void cleanup() {  
		// TODOAuto-generated method stub  
		
	}  

	public Map<String, Object> getComponentConfiguration() {  
		// TODOAuto-generated method stub  
		return null;  
	}  

}  

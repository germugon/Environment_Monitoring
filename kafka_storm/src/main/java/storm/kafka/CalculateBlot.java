package storm.kafka;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.StatisticsData;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import util.ConfigUtil;
import util.DateUtil;
import util.MySQLUtil;
import util.RedisUtil;

public class CalculateBlot implements IRichBolt{  
	
	private static final long serialVersionUID = 1L;
	private Jedis jedis;
	private OutputCollector collector;  

	private static final Logger logger = LoggerFactory.getLogger(CalculateBlot.class);
	
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {  
		jedis = RedisUtil.getJedis();
		this.collector = collector;  

	}  

	public void execute(Tuple input) {  
		String device = input.getString(0); 
		String name = input.getString(1); 
		Date time = null;
		try {
			time = DateUtil.string2Date(input.getString(2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String value = input.getString(3); 	
		logger.debug(device + " " + name + " " + DateUtil.date2String(time) + " " + value);
		
		double average = 0;
        int count = 0;
        
        if (time != null) {
			//从redis中获取一小时的数据
	        for (int i = 0; i < ConfigUtil.HOUR; i++) {
	            String dataTime = DateUtil.date2String(new Date(time.getTime() - i * 60 * 1000));
	            String dataValue = jedis.hget( device+ "-" +name, dataTime );
	            
	            if ( dataValue != null ) {
	                double val = Double.parseDouble(dataValue);
	                /**
	                 * 异常处理
	                 */
	                average += val;
	                count++;
	            }
	        }
	        //计算平均值
	        if (count != 0) {
	        	average /= count;
	            //保留两位小数(四舍五入)
	            BigDecimal b = new BigDecimal(average);
	            average = b.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
	            
	            StatisticsData data = new StatisticsData(device, name + "-" + "Avarage", time, average, ConfigUtil.HOUR );
	            logger.info(data.toString());
	            //存入Redis
	            jedis.hset(device + "-" + name + "-" + "Avarage", DateUtil.date2String(time), String.valueOf(average));
	            //存入MySQL
	            MySQLUtil.insert(data);
	            
	            collector.ack(input); 
	        }
			
        }
        else {
        	logger.error("处理后的日期时间格式为2016-02-29 23:45:00");
//        	collector.fail(input); 
        }
	}  

	public void cleanup() {  
		// TODOAuto-generated method stub  
		
	}  

	public void declareOutputFields(OutputFieldsDeclarer declarer) {         
		// TODOAuto-generated method stub  

	}  

	public Map<String, Object> getComponentConfiguration() {  
		// TODOAuto-generated method stub  
		return null;  
	}  

}  

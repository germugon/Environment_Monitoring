package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigUtil {
	/**
	 * 统计期，单位min
	 */
	public static final int MINUTE = 1;
	public static final int HOUR = 60;
	
	
	/**
	 * 数据格式
	 */
	public static final String DATA_VALID_FLAG = "1";
	public static final String STATION0 = "公司调试";
	public static final String STATION1 = "南宁环监站测试";
	public static final String STATION2 = "深圳环监站测试";
	
	public static final Map<String,List<String>> DATA_FORMAT = new HashMap<String,List<String>>();
	static {
		List<String> nameList0 = new ArrayList<String>();
		nameList0.add("DataTime");
		nameList0.add("Dust");
		nameList0.add("Noise");
		nameList0.add("Temperature");
		nameList0.add("Humidity");
		nameList0.add("Atmosphere");
		nameList0.add("WindSpeed");
		nameList0.add("WindDir");
		DATA_FORMAT.put(STATION0,nameList0);
		
		List<String> nameList1 = new ArrayList<String>();
		nameList1.add("DataTime");
		nameList1.add("LAFInstance");
		nameList1.add("LAFMaxMinute");
		nameList1.add("LAFeqMinute");
		nameList1.add("LAFPeakMinute");
		nameList1.add("LAFmaxMinute");
		nameList1.add("LAFeqHour");
		nameList1.add("LAF10Hour");
		nameList1.add("LAF90Hour");
		nameList1.add("LAF95Hour");
		nameList1.add("Dust");
		nameList1.add("WindSpeed");
		nameList1.add("WindDir");
		DATA_FORMAT.put(STATION1,nameList1);
		
		List<String> nameList2 = new ArrayList<String>();
		nameList2.add("DataTime");
		nameList2.add("Dust");
		DATA_FORMAT.put(STATION2,nameList2);
	};
	
	
	/**
	 * Redis配置
	 */
	//public static final String REDIS_IP = "192.168.10.100,192.168.10.101,192.168.10.102";  
	public static final String REDIS_IP = "192.168.10.100";  
	public static final int REDIS_PORT = 6379;  
	public static final String REDIS_SCHEME = "http://";
	public static final int REDIS_DB = 3; 
	public static final int REDIS_SSL = 60000;
	
	public static final int REDIS_MAXTOTAL = 50;  
	public static final int REDIS_MAXIDLE = 10;  
	public static final int REDIS_MINIDLE = 1;  
	public static final int REDIS_MAXWAITMILLIS = 30000;  
	
	
	/**
	 * MySQL配置
	 */
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://192.168.10.100:3306/huanjing";
	public static final String MYSQL_USR = "zhou";
	public static final String MYSQL_PWD = "zhou2016";
	
	public static final String STORE_TABLE = "statistics";
	
	
	/**
	 * KafkaSpout OutputFields
	 */
	public static final String MESSAGE_SCHEME_FIELD = "kafka_spout";
}

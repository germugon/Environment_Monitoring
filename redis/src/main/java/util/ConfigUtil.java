package util;

public class ConfigUtil {
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
	public static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/huanjing";
	public static final String MYSQL_USR = "zhou";
	public static final String MYSQL_PWD = "zhou2016";
	
	public static final String STORE_TABLE = "statistics";
	
}

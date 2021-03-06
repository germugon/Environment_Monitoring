package util;

/**
 * ScheduleJob使用的常量
 * @author ZHOU
 *
 */
public class ConfigUtil {

	/**
	 * Quartz任务调度
	 */
	//jobStatus
	public static final String RUN = "run";
	public static final String NOTRUN = "not run";
	
	/**
	 * 模拟数据频率,单位s
	 */
	public static final int FREQ_1MIN = 60;
//	public static final int FREQ_1MIN = 20;
	
	/**
	 * 定时任务
	 */
	public static final String JOB_NAME = "simulate_data";
	public static final String JOB_GROUP = "environment_monitor";
	public static final String CRON_EXPRESSION = "0 0/1 * * * ?";
	public static final String CLAZZ = "thread.ThreadManager";
	public static final String METHOD_NAME = "execute";
	public static final String JOB_STATUS = "run";
	
	/**
	 * 设备名
	 */
	public static final String DEVICE0 = "公司调试";
	public static final String DEVICE1 = "南宁环监站测试";
	public static final String DEVICE2 = "深圳环监站测试";
	
	/**
	 * scheduler关闭命令
	 */
	public static final String SHUTDOWN_CMD = "quit";
	
	/**
	 * scheduler默认关闭时间
	 */
	public static final int DEFAULT_SLEEP_TIME = 2;		//默认sleep时间（单位：min）
	
	
	/**
	 * ThreadManager参数名
	 */
	public static final String THREAD_NUM = "thread_num";
	
	/**
	 * WebService
	 */
	public final static String WEBSERVICE_URL = "http://localhost:8080/axis2/services/ProducerService?wsdl";
	public final static String WEBSERVICE_METHOD = "send";
}

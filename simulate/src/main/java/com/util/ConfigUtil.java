package com.util;

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
	//isConcurrent
	public static final String CONCURRENT = "true";
	public static final String NOTCONCURRENT = "false";
	
	/**
	 * 模拟数据频率,单位s
	 */
	public static final int FREQ_1MIN = 60;
//	public static final int FREQ_1MIN = 20;
	
}

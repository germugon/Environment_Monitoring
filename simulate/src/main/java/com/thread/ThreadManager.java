package com.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 生成模拟数据（多线程）
 * @author ZHOU
 *
 */
@Component
public class ThreadManager {

	private static ExecutorService threadPool;
	private ArrayList<DataBuilder> dataBuilders;		//applicationContext.xml中注入
	
	private static final Logger logger = Logger.getLogger(ThreadManager.class);
	
	public ThreadManager() {
		threadPool = Executors.newCachedThreadPool();
		
		dataBuilders = new ArrayList<DataBuilder>();
		//10个设备，每个设备为一个线程
		for( int i = 0; i < 10; i++ ) {
			dataBuilders.add(new DataBuilder(String.valueOf(i)));
		}
	}
	
	/**
	 * 生成原始数据
	 */
	public void execute(){
		for( DataBuilder dataBuilder : dataBuilders ) {
//			dataBuilder.execute();
			threadPool.submit(dataBuilder);	
		}
	}

	public static void shutdown(){
		threadPool.shutdown();
		logger.info("threadPool shutdown");
	}

	public void setDataBuilders(ArrayList<DataBuilder> dataBuilders) {
		this.dataBuilders = dataBuilders;
	}

}

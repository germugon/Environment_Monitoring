package thread;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import util.ConfigUtil;

/**
 * 生成模拟数据（多线程）
 * @author ZHOU
 *
 */
public class ThreadManager {

	private static ExecutorService threadPool;
	private ArrayList<AbstractDataBuilder> dataBuilders;
	
	private static final Logger logger = Logger.getLogger(ThreadManager.class);
	
	public ThreadManager( Map args ) {
		threadPool = Executors.newCachedThreadPool();
		
		dataBuilders = new ArrayList<AbstractDataBuilder>();
		//10个设备，每个设备为一个线程
		int threadNum = Integer.parseInt( (String) args.get(ConfigUtil.THREAD_NUM) );
		for( int i = 0; i < threadNum; i++ ) {
			dataBuilders.add(new CompanyDataBuilder(ConfigUtil.DEVICE0 + "-" + i));
//			dataBuilders.add(new NanNingDataBuilder(ConfigUtil.DEVICE1 + "-" + i));
//			dataBuilders.add(new ShenZhenDataBuilder(ConfigUtil.DEVICE2 + "-" + i));
		}
	}
	
	/**
	 * 生成原始数据
	 */
	public void execute(){
		for( AbstractDataBuilder dataBuilder : dataBuilders ) {
//			dataBuilder.execute();
			threadPool.submit(dataBuilder);	
		}
	}

	public static void shutdown(){
		threadPool.shutdown();
		logger.info("threadPool shutdown");
	}

	public void setDataBuilders(ArrayList<AbstractDataBuilder> dataBuilders) {
		this.dataBuilders = dataBuilders;
	}

}

package thread;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import util.ConfigUtil;
import webservice.ProducerWebserviceClient;

/**
 * 生成模拟数据的抽象类
 * @author ZHOU
 *
 */
public abstract class AbstractDataBuilder implements Runnable{
	
	protected String device;
	protected String data;
	
//	protected static final Logger flume_logger = Logger.getLogger(AbstractDataBuilder.class);
	protected static final Logger flume_logger = Logger.getLogger("flume_avro");
	
	public AbstractDataBuilder( String device ) {
//		device = UUID.randomUUID().toString();		//随机生成设备号
		this.device = device;
	}
	
	/**
	 * 模拟生成原始数据并推送到server
	 * @return
	 */
	public void run(){
		generateData();
		pushData();
	}

	/**
	 * 模拟生成原始数据
	 */
	public abstract void generateData();		

	/**
	 * 推送数据到server
	 * 调用webservice
	 */
	public void pushData() {
//		flume_logger.info(data);
		
		//指定调用方法的参数值  
    	Object[] parameters = new Object[] { data }; 
    	//指定调用方法返回值的数据类型  
    	Class[] returnTypes = new Class[] { String.class }; 
    	   
    	Object[] results = ProducerWebserviceClient.RPCServiceCall(ConfigUtil.WEBSERVICE_METHOD, parameters, returnTypes);
    	for(Object result : results) {
    		System.out.println(result); 
    	}
	}

	/**
	 * 生成指定范围内随默认值波动的随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @param range 波动幅度
	 */
	public double random( double default_value, double min, double max, double range ) {
		double value = -1000;
		int sign = 0;
		while( value < min || ( min < max && value > max ) ) {
			if( default_value <= min ) sign = 1;
			else if( default_value >= max ) sign = -1;
			else sign = (int) Math.pow( -1, Math.random() >= 0.5 ? 1 : 0 );
			value = default_value + sign * range * Math.random();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		String val = df.format(value);
		return Double.parseDouble(val);
	}
				
}

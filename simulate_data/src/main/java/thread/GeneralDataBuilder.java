package thread;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import entity.OriginalData;
import util.DateUtil;


/**
 * 生成模拟数据的抽象类
 * @author ZHOU
 *
 */
public class GeneralDataBuilder implements Runnable{
	
	private String device;
	private List<OriginalData> originalDataList;
	
	protected static final Logger logger = Logger.getLogger(GeneralDataBuilder.class);	
	
	public GeneralDataBuilder( String device ) {
//		device = UUID.randomUUID().toString();		//随机生成设备号
		this.device = device;
		originalDataList = new ArrayList<OriginalData>();
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
	public void generateData() {
		Date time = new Date();
//		originalDataList.add( new OriginalData("LAFInstant",random(55,20,120,10),time) );
//		//噪声统计值
//		originalDataList.add( new OriginalData("LAFMaxMinute",random(60,20,120,20),time) );
//		originalDataList.add( new OriginalData("LAeqMinute",random(55,20,120,10),time) );
//		originalDataList.add( new OriginalData("LCPeakMinute",random(85,20,120,10),time) );
//		originalDataList.add( new OriginalData("LAFmaxMinute",random(65,20,120,10),time) );
//		originalDataList.add( new OriginalData("LAeqHour",random(55,20,120,10),time) );
//		originalDataList.add( new OriginalData("LAF10Hour",random(55,20,120,10),time) );
//		originalDataList.add( new OriginalData("LAF90Hour",random(50,20,120,10),time) );
//		originalDataList.add( new OriginalData("LAF95Hour",random(50,20,120,10),time) );
//		
//		originalDataList.add( new OriginalData("Dust",random(15,0,50,10),time) );
//		originalDataList.add( new OriginalData("WindSpeed",random(1.5,0,50,1),time) );
//		originalDataList.add( new OriginalData("WindDir",random(300,0,360,60),time) );
		
		originalDataList.add( new OriginalData(device,"Dust",random(15,0,50,10),time) );
		originalDataList.add( new OriginalData(device,"Noise",random(55,20,120,10),time) );
		originalDataList.add( new OriginalData(device,"Temperature",random(10,-30,50,10),time) );
		originalDataList.add( new OriginalData(device,"Humidity",random(92,50,140,10),time) );
		originalDataList.add( new OriginalData(device,"Atmosphere",random(100,0,200,10),time) );
		originalDataList.add( new OriginalData(device,"WindSpeed",random(1.5,0,50,1),time) );
		originalDataList.add( new OriginalData(device,"WindDir",random(300,0,360,60),time) );
	}

	/**
	 * 推送数据到server
	 */
	public void pushData() {
		
		for( OriginalData originalData : originalDataList ) {
			String name = originalData.getName();
			double value = originalData.getValue();
			Date time = originalData.getTime();
			System.out.println( "device:" + device + ",name:" + name + ",time:" + DateUtil.date2String2(time) + ",value:" + value );
			/////////////////////////////////////////
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

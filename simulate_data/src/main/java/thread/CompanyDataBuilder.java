package thread;

import java.util.Date;

import org.apache.log4j.Logger;

import util.DateUtil;

/**
 * 生成模拟数据的抽象类
 * @author ZHOU
 *
 */
public class CompanyDataBuilder extends AbstractDataBuilder{
	
	public CompanyDataBuilder( String device ) {
		super(device);
	}
	
	/**
	 * 模拟生成原始数据
	 */
	public void generateData() {
		Date time = new Date();
		
		StringBuilder str = new StringBuilder();
		str.append(device + "::DataTime," + DateUtil.date2String2(time));
		str.append(",Dust," + random(15,0,50,10));
		str.append(",Noise," + random(55,20,120,10));
		str.append(",Temperature," + random(10,-30,50,10));
		str.append(",Humidity," + random(92,50,140,10));
		str.append(",Atmosphere," + random(100,0,200,10));
		str.append(",WindSpeed," + random(1.5,0,50,1));
		str.append(",WindDir," + random(300,0,360,60));
		
		data = str.toString();
		
//		originalDataList.add( new OriginalData(device,"Dust",random(15,0,50,10),time) );
//		originalDataList.add( new OriginalData(device,"Noise",random(55,20,120,10),time) );
//		originalDataList.add( new OriginalData(device,"Temperature",random(10,-30,50,10),time) );
//		originalDataList.add( new OriginalData(device,"Humidity",random(92,50,140,10),time) );
//		originalDataList.add( new OriginalData(device,"Atmosphere",random(100,0,200,10),time) );
//		originalDataList.add( new OriginalData(device,"WindSpeed",random(1.5,0,50,1),time) );
//		originalDataList.add( new OriginalData(device,"WindDir",random(300,0,360,60),time) );
	}

}

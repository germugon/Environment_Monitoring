package thread;

import java.util.Date;

import org.apache.log4j.Logger;

import util.DateUtil;

/**
 * 生成模拟数据的抽象类
 * @author ZHOU
 *
 */
public class NanNingDataBuilder extends AbstractDataBuilder	{	
	
	public NanNingDataBuilder( String device ) {
		super(device);
	}

	/**
	 * 模拟生成原始数据
	 */
	public void generateData() {
		Date time = new Date();
		
		StringBuilder str = new StringBuilder();
		str.append(device + "::1," + DateUtil.date2String2(time) + ",");
		str.append(random(55,20,120,10) + ",1,");
		str.append(random(60,20,120,20) + ",1,");
		str.append(random(55,20,120,10) + ",1,");
		str.append(random(85,20,120,10) + ",1,");
		str.append(random(65,20,120,10) + ",1,");
		str.append(random(55,20,120,10) + ",1,");
		str.append(random(55,20,120,10) + ",1,");
		str.append(random(50,20,120,10) + ",1,");
		str.append(random(50,20,120,10) + ",1,");
		str.append(random(15,0,50,10) + ",1,");
		str.append(random(1.5,0,50,1) + ",1,");
		str.append(random(300,0,360,60) + ",1,");
		
		data = str.toString();
		
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
	}
	
}

package thread;

import java.util.Date;

import org.apache.log4j.Logger;

import util.DateUtil;

/**
 * 生成模拟数据的抽象类
 * @author ZHOU
 *
 */
public class ShenZhenDataBuilder extends AbstractDataBuilder{
	
	public ShenZhenDataBuilder( String device ) {
		super(device);
	}

	/**
	 * 模拟生成原始数据
	 */
	public void generateData() {
		Date time = new Date();
		
		StringBuilder str = new StringBuilder();
		str.append(device + "::1," + DateUtil.date2String2(time) + ",");
		str.append(random(20,0,50,10) + ",1,");
		
		data = str.toString();
		
//		originalDataList.add( new OriginalData("Dust",random(20,0,50,10),time) );
		
	}
				
}

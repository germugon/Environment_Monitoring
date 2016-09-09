package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类型Date和String相互转换
 * @author ZHOU
 *
 */
public class DateUtil {

	/**
	 * Date转String
	 * @param date
	 * @return
	 */
	public static String date2String( Date date ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(date);
	}
	
	/**
	 * String转Date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date( String date ) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.parse(date);
	}
	
	/**
	 * Date转String
	 * 29/02/2016 23:45:00
	 * @param date
	 * @return
	 */
	public static String date2String2( Date date ) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		return sdf.format(date);
	}
	
	/**
	 * String转Date
	 * 29/02/2016 23:45:00
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date2( String date ) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		return sdf.parse(date);
	}
	
}

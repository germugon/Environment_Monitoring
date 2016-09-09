package mysql;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DateUtil;
import util.MySQLUtil;

public class MySQLTest {
	private static final Logger logger = LoggerFactory.getLogger(MySQLTest.class);
	private static final org.apache.log4j.Logger normalizedfile_logger = org.apache.log4j.Logger.getLogger("normalizedfile");

	public static void main(String[] args) {
		
		StatisticsData data = null;
		try {
			data = new StatisticsData("南宁环监站测试", "WindDir-Avarage", 
					DateUtil.string2Date("2016-03-10 00:01:00"), 357.388544, 60 );
			MySQLUtil.insert(data);
			normalizedfile_logger.info(data.toString());
			logger.info("Console:" + data.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

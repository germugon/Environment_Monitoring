package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import mysql.StatisticsData;

/**
 * MySQL数据库操作
 * @author ZHOU
 *
 */
public class MySQLUtil {
	private static Connection connection;
	
	/**
	 * 连接数据库
	 */
	public static void connect()
	{
		try {
			//注册驱动
			Class.forName(ConfigUtil.MYSQL_DRIVER);
			connection = DriverManager.getConnection(ConfigUtil.MYSQL_URL, ConfigUtil.MYSQL_USR, ConfigUtil.MYSQL_PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 关闭连接
	 */
	public static void disconnect() {
		try {
			if( connection != null )
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 插入统计数据
	 * @param result
	 * @return
	 */
	public static int insert( StatisticsData data ) {  

		int result = 0;
		
		try {
			connect();
			
			Date cur_time = new Date();
			String sql = "insert into " + ConfigUtil.STORE_TABLE 
					+ "(device,name,time,value,period,update_time) values(?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);         	
			statement.setString(1,data.getDevice());
			statement.setString(2,data.getName());
			statement.setTimestamp(3,new Timestamp(data.getTime().getTime()));
			statement.setDouble(4,data.getValue());
			statement.setInt(5,data.getPeriod()); 
			statement.setTimestamp(6,new Timestamp(cur_time.getTime()));
			result = statement.executeUpdate(); 
			
			disconnect(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}  
}

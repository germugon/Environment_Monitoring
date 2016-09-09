package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import entity.ScheduleJob;

/**
 * 定时任务执行
 * @author ZHOU
 *
 */
public class TaskUtil {
	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {

		//根据clazz（包名+类名）实例化对象（任务）
		if (scheduleJob.getClazz() != null) {
			try {
//				Object object = Class.forName(scheduleJob.getClazz()).newInstance();
				
				Constructor construct = Class.forName(scheduleJob.getClazz()).getDeclaredConstructor(
						new Class[]{Map.class});   
				construct.setAccessible(true);   
				Object object = construct.newInstance(new Object[]{scheduleJob.getParams()});  
		            
				//获取bean方法
				Method method = object.getClass().getMethod(scheduleJob.getMethodName());

				//调用方法
				if (method != null) {
					method.invoke(object);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("任务" + scheduleJob.getJobName() + "未启动成功");
			return;
		}
	}
}

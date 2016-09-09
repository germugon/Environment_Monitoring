package com.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.model.ScheduleJob;

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
		Object object = null;
		
		//通过反射找到bean，先按springId查找，再按beanClass（包名+类名）查找
		if (scheduleJob.getSpringId() != null) {
			object = SpringContextUtil.getBean(scheduleJob.getSpringId());
		} 
		else if (scheduleJob.getBeanClass() != null) {
			try {
				object = Class.forName(scheduleJob.getBeanClass()).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if (object == null) {
			System.out.println("任务" + scheduleJob.getJobName() + "未启动成功");
			return;
		}
		
		//获取bean方法
		Method method = null;
		try {
			method = object.getClass().getMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			System.out.println("任务" + scheduleJob.getJobName() + "未启动成功，方法名设置错误");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//调用方法
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
}

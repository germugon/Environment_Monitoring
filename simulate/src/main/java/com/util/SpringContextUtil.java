package com.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring WebApplication
 * @author ZHOU
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	/**
	 * 获取Bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}

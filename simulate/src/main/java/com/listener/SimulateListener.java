package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.thread.ThreadManager;

/**
 * 监听ServletContext对象（整个Web应用）的生命周期
 * @author ZHOU
 *
 */
public class SimulateListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(SimulateListener.class);
	
    public void contextInitialized(ServletContextEvent arg0) {
    	logger.info("SimulateListener");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    	ThreadManager.shutdown();
    }
	
}

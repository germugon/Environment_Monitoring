package com.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.model.ScheduleJob;
import com.util.TaskUtil;

/**
 * 串行，若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作    
 * @author ZHOU
 *
 */
@DisallowConcurrentExecution 
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtil.invokMethod(scheduleJob);
	}
	
	
}
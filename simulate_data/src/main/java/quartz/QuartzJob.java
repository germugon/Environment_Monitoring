package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import entity.ScheduleJob;
import util.TaskUtil;

/**
 * 并行，计划任务执行处无状态 
 * @author ZHOU
 *
 */
public class QuartzJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtil.invokMethod(scheduleJob);
	}
	
}
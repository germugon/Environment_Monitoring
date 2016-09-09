package quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.quartz.SchedulerException;

import entity.ScheduleJob;
import util.ConfigUtil;

public class QuartzMain {
	
	public static void main(String[] args) {
		
		if( args.length > 2 ) {
			System.out.println("\nUsage: java -jar xxx.jar [thread_num sleep_min]\n");
			System.out.println("Example1: java -jar simulate_data.jar 10");
			System.out.println("Input \"" + ConfigUtil.SHUTDOWN_CMD + "\" to shutdown scheduler.\n");
			System.out.println("Example2: java -jar simulate_data.jar 10 5");
			System.out.println("After 5 minutes, scheduler will be shutdown automatically.\n");
			return;
		}
		
		String threadNum = (args.length == 0) ? "10" : args[0];
		Map<String,String> params = new HashMap<String,String>();
		params.put(ConfigUtil.THREAD_NUM, threadNum);
		
		ScheduleJob job = new ScheduleJob(ConfigUtil.JOB_NAME, ConfigUtil.JOB_GROUP,
				ConfigUtil.CRON_EXPRESSION, ConfigUtil.CLAZZ, ConfigUtil.METHOD_NAME, ConfigUtil.JOB_STATUS, params);
		
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		jobList.add(job);
		
		try {
			ScheduleService scheduleService = new ScheduleService(jobList);
			scheduleService.start();
			
			if( args.length == 1 ) {
				Scanner in = new Scanner(System.in);
				while( in.hasNext() ) {
					String cmd = in.nextLine();
					if( cmd.equals(ConfigUtil.SHUTDOWN_CMD) ) {
						scheduleService.shutdown();
					}
				}
			}
			else if( args.length == 2 ) {
				Thread.sleep( Integer.parseInt(args[1]) * 60000 );
				scheduleService.shutdown();
			}
			else {
				Thread.sleep( ConfigUtil.DEFAULT_SLEEP_TIME * 60000 );
				scheduleService.shutdown();
			}
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

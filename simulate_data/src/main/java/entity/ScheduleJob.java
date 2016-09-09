package entity;

import java.util.Map;

public class ScheduleJob {

	private String jobName;
    private String jobGroup;
    private String cronExpression;
    private String clazz;
    private String methodName;
    private String jobStatus;
    private Map params;

	public ScheduleJob(String jobName, String jobGroup, String cronExpression, String clazz, String methodName,
			String jobStatus, Map params) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression = cronExpression;
		this.clazz = clazz;
		this.methodName = methodName;
		this.jobStatus = jobStatus;
		this.params = params;
	}
    
	public String getJobName() {
		return jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobGroup() {
		return jobGroup;
	}
	
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	public String getCronExpression() {
		return cronExpression;
	}
	
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	public String getClazz() {
		return clazz;
	}
	
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getJobStatus() {
		return jobStatus;
	}
	
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	} 

}
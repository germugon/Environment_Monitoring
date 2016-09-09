package com.entity;

import java.util.Date;

/**
 * 统计数据
 * @author ZHOU
 *
 */
public class StatisticsData {
	
	private String device;	//设备ID
	private String name;	//变量名
	private Date time;		//时间
	private double value;	//取值
	private int period;		//统计期（小时级）
	
	public StatisticsData(String device, String name, Date time, double value, int period) {
		super();
		this.device = device;
		this.name = name;
		this.time = time;
		this.value = value;
		this.period = period;
	}
	
	public String getDevice() {
		return device;
	}
	
	public void setDevice(String device) {
		this.device = device;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "StatisticsData [device=" + device + ", name=" + name + ", time=" + time + ", value=" + value
				+ ", period=" + period + "]";
	}

	
}

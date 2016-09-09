package entity;

import java.util.Date;

/**
 * 原始数据
 * @author ZHOU
 *
 */
public class OriginalData {
	private String device;	//设备名
	private String name;	//变量名
	private double value;	//取值
	private Date time;		//时间
	
	public OriginalData(String device, String name, double value, Date time) {
		super();
		this.device = device;
		this.name = name;
		this.value = value;
		this.time = time;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}

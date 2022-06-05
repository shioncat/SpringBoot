package com.gjun.domain;
//JavaBean

public class DhtData implements java.io.Serializable{


	private double temper; //溫度
	private double humi; //濕度
	private String location;
	public double getTemper() {
		return temper;
	}
	public void setTemper(double temper) {
		this.temper = temper;
	}
	public double getHumi() {
		return humi;
	}
	public void setHumi(double humi) {
		this.humi = humi;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
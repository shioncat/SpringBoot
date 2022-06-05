package com.gjun.domain;
//JavaBean
@SuppressWarnings("serial")
public class AppInformation implements java.io.Serializable{
	//封裝欄位/空參數架構子/實作序列化介面/setter getter
	private String appName;
	private String copyLight;
	private String address;
	private int id;
	//自訂建構子
	public AppInformation() {
		System.out.println("AppInformation物件產生");
		id=(int)(Math.random()*10000)+1;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCopyLight() {
		return copyLight;
	}
	public void setCopyLight(String copyLight) {
		this.copyLight = copyLight;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//Property setter and getter
	public int getId() {
		return id;
	}
	//overriding toString
	public String toString() {
		return "識別碼:"+id;
	}

}
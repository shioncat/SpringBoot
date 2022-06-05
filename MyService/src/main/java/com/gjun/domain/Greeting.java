package com.gjun.domain;
//JavaBean
//Model(MVC-M) 有時考量配合資料庫資料表結構
//有時考量配合到前端 UI Form Field(Form Bean)
public class Greeting {
	//封裝欄位
	private String who;
	private String message;
	//存取子
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}

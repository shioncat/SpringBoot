package com.gjun.domain;
//JavaBean
public class MemberBean {
	private String userName;
	private String password;
	private String realName;
	private String email;
	
	public MemberBean() {
		System.out.println("產生一個會員個體物件");
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}

package com.chxy.usedbook.vo;

public class User {

	private int userId;
	private String user_phone;
	private String userName; 
	private String userSex;
	private String userAddr;
	private int userAge;
	private String userPassword;
	private String userQQ;
	private String userSelfIntro;
	private int userState;
	
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserSelfIntro() {
		return userSelfIntro;
	}
	public void setUserSelfIntro(String userSelfIntro) {
		this.userSelfIntro = userSelfIntro;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", user_phone=" + user_phone
				+ ", userName=" + userName + ", userSex=" + userSex
				+ ", userAddr=" + userAddr + ", userAge=" + userAge
				+ ", userPassword=" + userPassword + ", userQQ=" + userQQ
				+ ", userSelfIntro=" + userSelfIntro + ", userState="
				+ userState + "]";
	}
		
	
}

package com.chxy.usedbook.vo;

import android.widget.EditText;
import android.widget.TextView;


public class Book {

	private String bookImage;
	private String bookName;
//	String bookAnnounce;
//	private String bookPerson;
	private User User;
//	private String bookSexImage;
	private String bookKind;
//	String bookPrice;	
	private String bookMoney;
	
	private String newOldDegree;
	private String originalPrice;
	private String phone;
	private String qq;
	private String weChat;
	private String personSay;
	private String time;
	
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookKind() {
		return bookKind;
	}
	public void setBookKind(String bookKind) {
		this.bookKind = bookKind;
	}
	public String getBookMoney() {
		return bookMoney;
	}
	public void setBookMoney(String bookMoney) {
		this.bookMoney = bookMoney;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	public String getNewOldDegree() {
		return newOldDegree;
	}
	public void setNewOldDegree(String newOldDegree) {
		this.newOldDegree = newOldDegree;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getPersonSay() {
		return personSay;
	}
	public void setPersonSay(String personSay) {
		this.personSay = personSay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}

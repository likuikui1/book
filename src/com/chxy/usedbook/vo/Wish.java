package com.chxy.usedbook.vo;

public class Wish {

	private WishUser User;
	private String wishTime;
	private String bookName;
	private String bookPrice;
	private String wishContent;
	private String newDegree;
	private String wishRmb;
	public WishUser getUser() {
		return User;
	}
	public void setUser(WishUser user) {
		User = user;
	}
	public String getWishTime() {
		return wishTime;
	}
	public void setWishTime(String wishTime) {
		this.wishTime = wishTime;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getWishContent() {
		return wishContent;
	}
	public void setWishContent(String wishContent) {
		this.wishContent = wishContent;
	}
	public String getNewDegree() {
		return newDegree;
	}
	public void setNewDegree(String newDegree) {
		this.newDegree = newDegree;
	}
	public String getWishRmb() {
		return wishRmb;
	}
	public void setWishRmb(String wishRmb) {
		this.wishRmb = wishRmb;
	}
	
}

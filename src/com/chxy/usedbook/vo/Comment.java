package com.chxy.usedbook.vo;
/**
 * 评论实体类
 * @author 李逵逵
 *
 */
public class Comment {
	private String imgUrl;
	private String name;
	private String time;
	private String comment_chat;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment_chat() {
		return comment_chat;
	}

	public void setComment_chat(String comment_chat) {
		this.comment_chat = comment_chat;
	}

}

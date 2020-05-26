package com.fanta.klat.model;

public class ChatRoom {
	private int crNum;
	private String crTitle;
	
	public int getCrNum() {
		return crNum;
	}
	public void setCrNum(int crNum) {
		this.crNum = crNum;
	}
	public String getCrTitle() {
		return crTitle;
	}
	public void setCrTitle(String crTitle) {
		this.crTitle = crTitle;
	}
	@Override
	public String toString() {
		return "ChatRoom [crNum=" + crNum + ", crTitle=" + crTitle + "]";
	}
}
package com.fanta.klat.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ChatMessage {
	private int cmNum;
	private String cmContent;
	private String cmType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cmWriteDate;
	private int crNum;
	private int mNum;
	private int mName;
	private int mProfileImg;
	
	public int getCmNum() {
		return cmNum;
	}
	public void setCmNum(int cmNum) {
		this.cmNum = cmNum;
	}
	public String getCmContent() {
		return cmContent;
	}
	public void setCmContent(String cmContent) {
		this.cmContent = cmContent;
	}
	public String getCmType() {
		return cmType;
	}
	public void setCmType(String cmType) {
		this.cmType = cmType;
	}
	public Date getCmWriteDate() {
		return cmWriteDate;
	}
	public void setCmWriteDate(Date cmWriteDate) {
		this.cmWriteDate = cmWriteDate;
	}
	public int getCrNum() {
		return crNum;
	}
	public void setCrNum(int crNum) {
		this.crNum = crNum;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getmName() {
		return mName;
	}
	public void setmName(int mName) {
		this.mName = mName;
	}
	public int getmProfileImg() {
		return mProfileImg;
	}
	public void setmProfileImg(int mProfileImg) {
		this.mProfileImg = mProfileImg;
	}
	@Override
	public String toString() {
		return "ChatMessage [cmNum=" + cmNum + ", cmContent=" + cmContent + ", cmType=" + cmType + ", cmWriteDate="
				+ cmWriteDate + ", crNum=" + crNum + ", mNum=" + mNum + ", mName=" + mName + ", mProfileImg="
				+ mProfileImg + "]";
	}
}
package com.fanta.klat.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ChatMessage {
	private int cmNum;
	private String cmContent;
	private String cmType;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date cmWriteDate;
	private int crNum;
	private int mNum;
	
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
	@Override
	public String toString() {
		return "ChatMessage [cmNum=" + cmNum + ", cmContent=" + cmContent + ", cmType=" + cmType + ", cmWriteDate="
				+ cmWriteDate + ", crNum=" + crNum + ", mNum=" + mNum + "]";
	}
}
package com.fanta.klat.model;

import java.util.Date;

public class ChatMessage {
	private int cmNum;
	private String cmContent;
	private String cmType;
	private Date cmWriteDate;
	
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
	@Override
	public String toString() {
		return "ChatMessage [cmNum=" + cmNum + ", cmContent=" + cmContent + ", cmType=" + cmType + ", cmWriteDate="
				+ cmWriteDate + "]";
	}
}
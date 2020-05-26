package com.fanta.klat.model;

public class Member {
	
	private int mNum;
	private String mId;
	private String mName;
	private String mPw;
	private String mProfileImg;
	
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmProfileImg() {
		return mProfileImg;
	}
	public void setmProfileImg(String mProfileImg) {
		this.mProfileImg = mProfileImg;
	}
	
	@Override
	public String toString() {
		return "Member [mNum=" + mNum + ", mId=" + mId + ", mName=" + mName + ", mPw=" + mPw + ", mProfileImg="
				+ mProfileImg + "]";
	}

}

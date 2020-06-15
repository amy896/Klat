package com.fanta.klat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tbl_member")
@DynamicUpdate
public class Member implements Serializable {

=======
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tbl_member")
@DynamicUpdate
public class Member implements Serializable{
	
>>>>>>> refs/heads/amy
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_num")
	private int mNum;

	@Column(name = "m_id", length = 200, nullable = false)
	private String mId;

	@Column(name = "m_name", length = 200, nullable = false)
	private String mName;

	@Column(name = "m_pw", length = 200, nullable = false)
	private String mPw;

	@Column(name = "m_profile_img", length = 200, nullable = false)
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

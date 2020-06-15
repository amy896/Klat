package com.fanta.klat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_chat_room_member")
public class ChatRoomMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crm_num")
	private int crmNum;

	@Column(name = "cr_num")
	private int crNum;

	@Column(name = "m_num")
	private int mNum;

	public int getCrmNum() {
		return crmNum;
	}

	public void setCrmNum(int crmNum) {
		this.crmNum = crmNum;
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
		return "ChatRoomMember [crmNum=" + crmNum + ", crNum=" + crNum + ", mNum=" + mNum + "]";
	}

}

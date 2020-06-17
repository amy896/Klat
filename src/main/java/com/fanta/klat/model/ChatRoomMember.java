package com.fanta.klat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "m_num", referencedColumnName = "m_num", insertable = false, updatable = false)
	private Member member;

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

	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "ChatRoomMember [crmNum=" + crmNum + ", crNum=" + crNum + ", mNum=" + mNum + "]";
	}

}

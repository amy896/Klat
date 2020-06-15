package com.fanta.klat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@IdClass(AuthorityId.class)
@Table(name = "tbl_authority")
public class Authority {
	@Id
	@Column(name = "m_num")
	private int mNum;
	@Id
	@Column(name = "a_authority")
	@ColumnDefault("ROLE_USER")
	private String authority;

	public int getmNum() {
		return mNum;
	}

	public void setmNum(int mNum) {
		this.mNum = mNum;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@PrePersist
	public void prePersist() {
		this.authority = this.authority == null ? "ROLE_USER" : this.authority;
	}

	@Override
	public String toString() {
		return "Authority [mNum=" + mNum + ", authority=" + authority + "]";
	}

}

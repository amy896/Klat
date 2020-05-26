package com.fanta.klat.dao;

import com.fanta.klat.model.Member;

public interface MemberDao {

	public int insertMember(Member member);
	
	public int insertAuthority(int mNum);
	
	/*
	public Member selectMemberByMId(String mId);
	
	public Member selectMemberByMNum(int mNum);
	*/

}

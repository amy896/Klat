package com.fanta.klat.dao;

import java.util.List;

import com.fanta.klat.model.Member;

public interface MemberDao {

	public int insertMember(Member member);

	public int insertAuthority(int mNum);
	
	public int deleteMemberByMNum(int mNum);
	
	public int deleteAuthority(int mNum);

	public int updateMember(Member member);
	
	public Member selectMemberByMId(String mId);

	public Member selectMemberByMNum(int mNum);

	public List<String> selectAuthoritiesByMNum(int mNum);

	public List<String> selectMemberByKeyword(String keyword);
}

package com.fanta.klat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.dao.MemberDao;
import com.fanta.klat.model.Member;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Transactional
	public boolean signUpMember(Member member) {
		if(memberDao.insertMember(member)>0)
			if(memberDao.insertAuthority(member.getmNum())>0)
				return true;
		return false;
	}
	
	public Member getMemberByMId(String mId) {
		return memberDao.selectMemberByMId(mId);
	}
	
	public Member getMemberByMNum(int mNum) {
		return memberDao.selectMemberByMNum(mNum);
	}

	public List<String> getAuthoritiesByMNum(int mNum){
		return memberDao.selectAuthoritiesByMNum(mNum);
	}

}

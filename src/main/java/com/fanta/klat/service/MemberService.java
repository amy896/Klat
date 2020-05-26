package com.fanta.klat.service;

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

}

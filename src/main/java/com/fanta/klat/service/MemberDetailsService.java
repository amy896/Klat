package com.fanta.klat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fanta.klat.model.Authority;
import com.fanta.klat.model.Member;
import com.fanta.klat.model.MemberDetails;

@Component
public class MemberDetailsService implements UserDetailsService {

	@Autowired
	private MemberService memberService;

	//by 미경, 멤버 아이디 기준으로 멤버정보 및 권한 가져오기 
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		Member originalMember = memberService.getMemberByMId(userid);
		String mPw = originalMember.getmPw();
		int mNum = originalMember.getmNum();
		List<Authority> authList = memberService.getAuthoritiesByMNum(mNum);
		MemberDetails member = new MemberDetails();
		member.setmId(userid);
		member.setmPw(mPw);
		for (Authority auth : authList) {
			member.addAuth(auth.getAuthority());
		}
		return member;
	}
}

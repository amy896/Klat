package com.fanta.klat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanta.klat.model.Member;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/signupform", method = RequestMethod.GET)
	public String showSignUpForm() {
		return "member/signUpForm";
	}

	@RequestMapping(value = "/signupmember", method = RequestMethod.POST)
	public String signUpMember(String userid, String username, String userpassword) {
		Member member = new Member();
		member.setmId(userid);
		member.setmName(username);
		member.setmPw(userpassword);
		memberService.signUpMember(member);
		return "redirect:/member/signinform";
	}

	@RequestMapping(value = "/signinform", method = RequestMethod.GET)
	public String showSignInForm() {
		return "member/signInForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkUserId", method = RequestMethod.GET)
	public boolean checkUserId(String userId) {
		Member member = memberService.getMemberByMId(userId);
		if(member == null) {
			return false;
		}
		return true;
	} 
	
}

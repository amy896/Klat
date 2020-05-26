package com.fanta.klat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanta.klat.model.Member;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/signUpForm", method = RequestMethod.GET)
	public String showSignUpForm() {
		return "member/signUpForm";
	}
	
	@RequestMapping(value = "/signUpMember", method = RequestMethod.POST)
	public String signUpMember(Member member) {
		memberService.signUpMember(member);
		return null;
	}
}

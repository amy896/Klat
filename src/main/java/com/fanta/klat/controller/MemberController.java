package com.fanta.klat.controller;

import java.util.Random;

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

	//by 미경, 회원가입 화면 보여주기 
	@RequestMapping(value = "/signupform", method = RequestMethod.GET)
	public String showSignUpForm() {
		return "member/signUpForm";
	}
	
	//by 미경, 회원가입 하기(service에서 권한 추가 진행)
	@RequestMapping(value = "/signupmember", method = RequestMethod.POST)
	public String signUpMember(String userid, String username, String userpassword) {
		Member member = new Member();
		member.setmId(userid);
		member.setmName(username);
		member.setmPw(userpassword);
		int randomNum = new Random().nextInt(10);
		member.setmProfileImg("type"+randomNum);
		memberService.signUpMember(member);
		return "redirect:/member/signinform";
	}

	//by 미경, 로그인 화면 보여주기 
	@RequestMapping(value = "/signinform", method = RequestMethod.GET)
	public String showSignInForm() {
		return "member/signInForm";
	}
	
	//by 미경, 회원가입시 중복된 아이디 확인하기 
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

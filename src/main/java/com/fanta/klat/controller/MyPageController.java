package com.fanta.klat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@RequestMapping("/mypagemain")
	public String showMyPageMain() {
		
		return "myPage/myPageMain";
	}
	
	@RequestMapping("/getmember")
	public String getMember() {
		return "redirect:mypagemain";
	}
	
	@RequestMapping("/removemember")
	public String removeMember() {
		return "redirect:mypagemain";
	}
	
	@RequestMapping("/modifyform")
	public String showModifyForm() {
		return "myPage/myPageModifyForm";
	}
	
	@RequestMapping("/modifymember")
	public String modifyMember() {
		return "redirect:mypagemain";
	}
}

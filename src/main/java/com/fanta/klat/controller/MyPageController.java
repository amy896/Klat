package com.fanta.klat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanta.klat.model.Member;
import com.fanta.klat.service.ChatRoomService;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@Autowired
	MemberService memberService;
	@Autowired
	ChatRoomService chatRoomService;

	//by 혜선, 마이 페이지 화면 보여주기
	@RequestMapping("/mypagemain")
	public String showMyPageMain(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		Member member = memberService.getMemberByMNum(mNum);
		model.addAttribute("member", member);
		session.setAttribute("member", member);
		return "myPage/myPageMain";
	}

	//by 혜선, 탈퇴 및 모든 채팅방에서 나가기
	@RequestMapping("/removemember")
	public String removeMember(int mnum) {
		chatRoomService.exitAllChatRoom(mnum);
		memberService.removeMember(mnum);
		return "redirect:../main";
	}

	//by 혜선, 개인정보 수정 화면 보여주기
	@RequestMapping("/modifyform")
	public String showModifyForm(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		Member member = memberService.getMemberByMNum(mNum);
		model.addAttribute("member", member);
		return "myPage/myPageModifyForm";
	}

	//by 혜선, 개인정보 수정하기
	@RequestMapping(value = "/modifymember", method = RequestMethod.POST)
	public String modifyMember(String mname, String mpw, String mProfileImg, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		member.setmName(mname);
		member.setmPw(mpw);
		member.setmProfileImg(mProfileImg);
		memberService.modifyMember(member);
		return "redirect:/mypage/mypagemain";
	}
}

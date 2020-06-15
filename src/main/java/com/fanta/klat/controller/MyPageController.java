package com.fanta.klat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanta.klat.model.Member;
import com.fanta.klat.repository.ChatRoomRepository;
import com.fanta.klat.service.ChatRoomService;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	MemberService memberService;
	@Autowired
	ChatRoomService chatRoomService;

	@RequestMapping("/mypagemain")
	public String showMyPageMain(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		Member member = memberService.getMemberByMNum(mNum);
		model.addAttribute("member", member);
		session.setAttribute("member", member);
		System.out.println("mypagemain = " + memberService.getChatMemberListExceptMe(10, mNum));
		return "myPage/myPageMain";
	}

	@RequestMapping("/getmember")
	public String getMember() {
		return "redirect:mypagemain";
	}

	@RequestMapping("/removemember")
	public String removeMember(int mnum) {
		memberService.removeMember(mnum);
		chatRoomService.exitAllChatRoom(mnum);
		return "redirect:../main";
	}

	@RequestMapping("/modifyform")
	public String showModifyForm(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		Member member = memberService.getMemberByMNum(mNum);
		model.addAttribute("member", member);
		return "myPage/myPageModifyForm";
	}

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

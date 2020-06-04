package com.fanta.klat.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping("/mypagemain")
	public String showMyPageMain(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		Member member = memberService.getMemberByMNum(mNum);
		model.addAttribute("member", member);
		session.setAttribute("member", member);
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

	@ResponseBody
	@RequestMapping(value = "/modifymember", method = RequestMethod.POST)
	public boolean  modifyMember(@RequestParam(value = "croppedImage", required = false)MultipartFile croppedImage, 
									@RequestParam(value = "profileImgType", required = false)String profileImgType, 
									String mname, String mpw, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		String uploadFolder = "/Users/amy/Desktop/profileImage";
		String saveFileName = null;
		File saveFile = new File(uploadFolder, member.getmId());
		
		if(croppedImage != null) {
			try {
				croppedImage.transferTo(saveFile);
				saveFileName = member.getmId();
			} catch (Exception e) {
				return false;
			}
		}
		return  memberService.modifyMember(member.getmNum(), mname, mpw, saveFileName);
	}

}

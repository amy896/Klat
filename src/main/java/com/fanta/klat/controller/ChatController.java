package com.fanta.klat.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanta.klat.model.ChatRoom;
import com.fanta.klat.model.Member;
import com.fanta.klat.service.ChatRoomService;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatRoomService crService;
	@Autowired
	private MemberService memberService;

	
	@RequestMapping("/chatmain")
	public String showChatMain(Principal principal, HttpSession session) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		return "chat/chatMain";
	}
	
	@RequestMapping("/chatroom")
	public String showChatRoom(Principal principal, HttpSession session, @RequestParam(defaultValue = "0") int crnum) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		return "chat/chatRoom";
	}

	@RequestMapping("/addform")
	public String showAddForm(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		model.addAttribute("mNum", mNum);
		return "chat/chatAddForm";
	}

	@RequestMapping("/addchatroom")
	public String addChatRoom(HttpSession session, String crtitle) {
		int mNum = (Integer) session.getAttribute("mNum");
		int crNum = crService.addChatRoom(mNum, crtitle);
		return "redirect:chatroom?crnum=" + crNum;
	}
	
	@ResponseBody
	@RequestMapping("/modifychatroom")
	public boolean modifyChatRoom(HttpSession session, Model model, int crnum, String crtitle) {
		return crService.modifyChatRoom(crnum, crtitle);
	}
	
	@ResponseBody
	@RequestMapping("/exitchatroom")
	public boolean exitChatRoom(HttpSession session, int crnum) {
		int mNum = (Integer) session.getAttribute("mNum");
		return crService.exitChatRoom(crnum, mNum);
	}

	@ResponseBody
	@RequestMapping("/getchatroomlist")
	public List<ChatRoom> getChatRoomList(HttpSession session) {
		int mNum = (Integer) session.getAttribute("mNum");
		List<ChatRoom> chatRoomList = crService.getChatRoomListByMNum(mNum);
		return chatRoomList;
	}
}

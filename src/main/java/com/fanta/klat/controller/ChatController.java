package com.fanta.klat.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanta.klat.service.ChatRoomService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatRoomService crService;
	
	@RequestMapping("/chatroom")
	public String showChatRoom(Principal principal, HttpSession session, int crnum) {
		int mNum = 1;
		session.setAttribute("mNum", mNum);
		return "/chat/chatRoom";
	}
	
	@RequestMapping("/addform")
	public String showAddForm(HttpSession session, Model model, String crtitle) {
		int mNum = (Integer) session.getAttribute("mNum");
		model.addAttribute("mNum", mNum);
		return "/chat/addAddForm";
	}
	
	@RequestMapping("/addchatroom")
	public String addChatRoom(HttpSession session, String crtitle) {
		int mNum = (Integer) session.getAttribute("mNum");
		int crNum = crService.addChatRoom(mNum, crtitle);
		return "redirect:chatRoom?crNum="+crNum;
	}
}

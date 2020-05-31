package com.fanta.klat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.model.ChatRoom;
import com.fanta.klat.model.Member;
import com.fanta.klat.service.ChatMessageService;
import com.fanta.klat.service.ChatRoomService;
import com.fanta.klat.service.MemberService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatRoomService crService;
	@Autowired
	private ChatMessageService cmService;
	@Autowired
	private MemberService memberService;

	@RequestMapping("/chatmain")
	public String showChatMain(Principal principal, HttpSession session) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);

		List<Map<String, Object>> chatInfoList = new ArrayList<Map<String, Object>>();
		List<ChatRoom> chatList = crService.getChatRoomListByMNum(mNum);
		for (ChatRoom chat : chatList) {
			Map<String, Object> chatInfo = new HashMap<String, Object>();
			chatInfo.put("chat", chat);
			chatInfo.put("chatMemberList", memberService.getChatMemberListExceptMe(chat.getCrNum(), mNum));
			chatInfoList.add(chatInfo);
		}

		model.addAttribute("member", member);
		model.addAttribute("chatInfoList", chatInfoList);
		return "chat/chatMain";
	}
	
	@RequestMapping("/chatroom")
	public String showChatRoom(Principal principal, HttpSession session, @RequestParam(defaultValue = "0") int crnum, Model model) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		session.setAttribute("crNum", crnum);
		
		List<ChatMessage> chatMessageList = cmService.getAllChatMessageByCrNum(crnum);
		model.addAttribute("chatMessageList", chatMessageList);
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
	
	@RequestMapping("/exitchatroom")
	public String exitChatRoom(HttpSession session, int crnum) {
		int mNum = (Integer) session.getAttribute("mNum");
		int crNum = (Integer) session.getAttribute("crNum");
		crService.exitChatRoom(crNum, mNum);
		return "redirect:chatmain";
	}

	@ResponseBody
	@RequestMapping("/getchatroomlist")
	public List<ChatRoom> getChatRoomList(HttpSession session) {
		int mNum = (Integer) session.getAttribute("mNum");
		List<ChatRoom> chatRoomList = crService.getChatRoomListByMNum(mNum);
		return chatRoomList;
	}
	
	@RequestMapping("/inviteform")
	public String showInviteForm() {
		return "chat/chatInviteForm";
	}
	
	@RequestMapping("/invitemember")
	public String inviteMember(HttpSession session) {
		int crNum = (Integer) session.getAttribute("crNum");
		return "redirect:chatroom?crnum=" + crNum;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchmemberlist")
	public List<String> searchMemberList(HttpSession session, @RequestParam(value = "keyword") String keyword, String mid) {
		int crNum = (Integer) session.getAttribute("crNum");
		int mNum = (Integer) session.getAttribute("mNum");
		return memberService.searchMemberList(keyword, crNum, mNum);
	}
	
	@RequestMapping(value = "/sendchatmessage")
	public void sendChatMessage(HttpSession session, String cmContent, String cmType, Date cmWriteDate) {
		int crNum = (Integer) session.getAttribute("crNum");
		int mNum = (Integer) session.getAttribute("mNum");
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(cmContent);
		chatMessage.setCmType(cmType);
		chatMessage.setCmWriteDate(cmWriteDate);
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		cmService.sendChatMessage(chatMessage);
	}
}

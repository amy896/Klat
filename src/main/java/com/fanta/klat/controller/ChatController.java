package com.fanta.klat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
	@Autowired
	private SimpMessagingTemplate smt;

	@RequestMapping("/chatmain")
	public String showChatMain(Principal principal, HttpSession session, Model model) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		session.setAttribute("member", member);

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
	public String showChatRoom(Principal principal, HttpSession session, @RequestParam(defaultValue = "0") int crnum,
			Model model) {
		ChatRoom chatroom = crService.getChatRoomByCrNum(crnum);
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		session.setAttribute("crNum", crnum);

		List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
		model.addAttribute("chatroom", chatroom);
		model.addAttribute("member", member);
		model.addAttribute("chatMessageList", chatMessageList);
		return "chat/chatRoom";
	}

	@ResponseBody
	@RequestMapping("/loadallmessage")
	public List<ChatMessage> loadAllMessage(@RequestParam("crNum") int crNum) {
		List<ChatMessage> chatMessageList = cmService.getAllChatMessageByCrNum(crNum);
		return chatMessageList;
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
	@RequestMapping(value = "/exitchatroom", produces = "text/plain;charset=UTF-8")
	public String exitChatRoom(HttpSession session, int crnum) {
		Member member = (Member) session.getAttribute("member");
		boolean exitChatroomResult = crService.exitChatRoom(crnum, member.getmNum());
		String jsonStr = "{\"exitChatroomResult\":\"" + exitChatroomResult + "\",\"crNum\":\"" + crnum + "\",\"receiverName\":\"" + member.getmName() + "\"}";
		return jsonStr;
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

	@ResponseBody
	@RequestMapping(value = "/invitemember", produces = "text/plain;charset=UTF-8")
	public String inviteMember(HttpSession session, String mid) {
		int crNum = (Integer) session.getAttribute("crNum");
		Member receiver = memberService.getMemberByMId(mid);
		boolean addMemberResult = crService.addChatRoomMember(crNum, receiver.getmNum());
		String jsonStr = "{\"addMemberResult\":\"" + addMemberResult + "\",\"crNum\":\"" + crNum + "\",\"receiverName\":\"" + receiver.getmName() + "\"}";
		return jsonStr;
	}

	@ResponseBody
	@RequestMapping(value = "/searchmemberlist")
	public List<String> searchMemberList(HttpSession session, @RequestParam(value = "keyword") String keyword,
			String mid) {
		int crNum = (Integer) session.getAttribute("crNum");
		int mNum = (Integer) session.getAttribute("mNum");
		return memberService.searchMemberList(keyword, crNum, mNum);
	}

	@SendTo("/category/msg/{var2}")
	@MessageMapping("/sendChatMessage/{var1}/{var2}")
	public ChatMessage sendChatMessage(@DestinationVariable(value = "var1") int mNum,
			@DestinationVariable(value = "var2") int crNum, String cmContent) {
		String msgType = "message";
		if(mNum == -1) {
			msgType = "systemMessage";
		}
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(cmContent);
		chatMessage.setCmType(msgType);
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		int cmNum = cmService.sendChatMessage(chatMessage);
		ChatMessage cm = cmService.getChatMessageByCmNum(cmNum);
		return cm;
	}

}

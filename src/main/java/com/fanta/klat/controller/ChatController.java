package com.fanta.klat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.fanta.klat.service.SystemMessageService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private SystemMessageService systemMessageService;
	@Autowired
	private SimpMessagingTemplate smt;

	//by 미경, 채팅 메인(참여한 채팅 리스트) 보여주기 
	@RequestMapping("/chatmain")
	public String showChatMain(Principal principal, HttpSession session, Model model) {
		String mId = principal.getName();
		Member member = memberService.getMemberByMId(mId);
		int mNum = member.getmNum();
		session.setAttribute("mNum", mNum);
		session.setAttribute("member", member);

		List<Map<String, Object>> chatInfoList = new ArrayList<Map<String, Object>>();
		List<ChatRoom> chatList = chatRoomService.getChatRoomListByMNum(mNum);
		for (ChatRoom chat : chatList) {
			Map<String, Object> chatInfo = new HashMap<String, Object>();
			chatInfo.put("chat", chat);
			List<Member> chatMemberList = memberService.getChatMemberListExceptMe(chat.getCrNum(), mNum);
			chatInfo.put("chatMemberList", chatMemberList);
			chatInfoList.add(chatInfo);
		}
		model.addAttribute("member", member);
		model.addAttribute("chatInfoList", chatInfoList);
		return "chat/chatMain";
	}

	//by 혜선, 채팅방 화면 보여주기 
	@RequestMapping("/chatroom")
	public String showChatRoom(Principal principal, HttpSession session, @RequestParam(defaultValue = "0") int crnum, Model model) {
		ChatRoom chatroom = chatRoomService.getChatRoomByCrNum(crnum);
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

	//by 혜선, 채팅방 내 모든 채팅메시지 가져오기
	@ResponseBody
	@RequestMapping("/loadallmessage")
	public List<ChatMessage> loadAllMessage(@RequestParam("crNum") int crNum) {
		List<ChatMessage> chatMessageList = chatMessageService.getAllChatMessageByCrNum(crNum);
		return chatMessageList;
	}

	//by 혜선, 채팅방 추가 화면 보여주기
	@RequestMapping("/addform")
	public String showAddForm(HttpSession session, Model model) {
		int mNum = (Integer) session.getAttribute("mNum");
		model.addAttribute("mNum", mNum);
		return "chat/chatAddForm";
	}

	//by 혜선, 채팅방 추가하기
	@RequestMapping("/addchatroom")
	public String addChatRoom(HttpSession session, String crtitle) {
		int mNum = (Integer) session.getAttribute("mNum");
		int crNum = chatRoomService.addChatRoom(mNum, crtitle);
		return "redirect:chatroom?crnum=" + crNum;
	}

	//by 혜선, 채팅방 제목 수정하기
	@ResponseBody
	@RequestMapping("/modifychatroom")
	public boolean modifyChatRoom(HttpSession session, Model model, int crnum, String crtitle) {
		return chatRoomService.modifyChatRoom(crnum, crtitle);
	}

	//by 혜선, 채팅방 나가기
	@RequestMapping(value = "/exitchatroom")
	public String exitChatRoom(HttpSession session, int crnum) {
		int mNum = (Integer) session.getAttribute("mNum");
		chatRoomService.exitChatRoom(crnum, mNum);
		Member member = memberService.getMemberByMNum(mNum);
		ChatMessage chatMessage = systemMessageService.sendExitMessage(crnum, member);
		smt.convertAndSend("/category/systemMsg/" + crnum, chatMessage);
		return "redirect:chatmain";
	}

	//by 미경, 네비게이션에 (참여한 채팅 리스트) 보여주기 
	@ResponseBody
	@RequestMapping("/getchatroomlist")
	public List<ChatRoom> getChatRoomList(HttpSession session) {
		int mNum = (Integer) session.getAttribute("mNum");
		List<ChatRoom> chatRoomList = chatRoomService.getChatRoomListByMNum(mNum);
		return chatRoomList;
	}

	//by 혜선, 채팅방 초대 화면 보여주기
	@RequestMapping("/inviteform")
	public String showInviteForm(HttpSession session,  Model model, int crnum) {
		int mNum = (Integer) session.getAttribute("mNum");
		model.addAttribute("crNum", crnum);
		model.addAttribute("mNum", mNum);
		return "chat/chatInviteForm";
	}

	//by 혜선, 채팅방에 초대할 수 있는 멤버 리스트 가져오기
	@ResponseBody
	@RequestMapping(value = "/searchmemberlist")
	public List<Member> searchMemberList(HttpSession session, @RequestParam(value = "keyword") String keyword, int crNum, int mNum) {
		List<Member> memberList = memberService.searchMemberList(keyword, crNum, mNum);
		return memberList;
	}
	
	//by 혜선, 채팅방 초대하기
	@RequestMapping(value = "/invitemember")
	public String inviteMember(HttpSession session, String mid) {
		int crNum = (Integer) session.getAttribute("crNum");
		int mNum = memberService.getMemberByMId(mid).getmNum();
		chatRoomService.addChatRoomMember(crNum, mNum);
		Member member = memberService.getMemberByMNum(mNum);
		ChatMessage chatMessage = systemMessageService.sendEntranceMessage(crNum, member);
		smt.convertAndSend("/category/systemMsg/" + crNum, chatMessage);
		return "redirect:chatroom?crnum=" + crNum;
	}

	//by 혜선, 채팅메시지 보내기
	@SendTo("/category/msg/{var2}")
	@MessageMapping("/sendChatMessage/{var1}/{var2}")
	public ChatMessage sendChatMessage(@DestinationVariable(value = "var1") int mNum,
									   @DestinationVariable(value = "var2") int crNum, String cmContent) {
		String msgType = "message";
		if (mNum == -1) msgType = "systemMessage";
		
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(cmContent);
		chatMessage.setCmType(msgType);
		chatMessage.setCmWriteDate(new Date());
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		ChatMessage chatMessageSent = chatMessageService.sendChatMessage(chatMessage);
		return chatMessageSent;
	}
}
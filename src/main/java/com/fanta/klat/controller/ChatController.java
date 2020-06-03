package com.fanta.klat.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

		List<ChatMessage> chatMessageList = cmService.getAllChatMessageByCrNum(crnum);
		model.addAttribute("chatroom", chatroom);
		model.addAttribute("member", member);
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
	public List<String> searchMemberList(HttpSession session, @RequestParam(value = "keyword") String keyword,
			String mid) {
		int crNum = (Integer) session.getAttribute("crNum");
		int mNum = (Integer) session.getAttribute("mNum");
		return memberService.searchMemberList(keyword, crNum, mNum);
	}

	@SendTo("/category/msg/{var2}")
	@MessageMapping("/sendChatMessage/{var1}/{var2}/{var3}")
	public ChatMessage sendChatMessage( 
			@DestinationVariable(value = "var1") int mNum,
			@DestinationVariable(value = "var2") int crNum,
			@DestinationVariable(value = "var3") String cmContent) {

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(cmContent);
		chatMessage.setCmType("message");
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		System.out.println("chatMessage : "+chatMessage);
		int cmNum = cmService.sendChatMessage(chatMessage);
		ChatMessage cm = cmService.getChatMessageByCmNum(cmNum);
		return cm;
	}
	
	@SendTo("/category/msg/{var2}")
	@MessageMapping("/sendCode/{var1}/{var2}/{var3}")
	public ChatMessage sendCode(String code,
			@DestinationVariable(value = "var1") int mNum,
			@DestinationVariable(value = "var2") int crNum,
			@DestinationVariable(value = "var3") String type) {
		
		if(type.equals("java")) type = "text/x-java";
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(code);
		chatMessage.setCmType(type);
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		cmService.sendChatMessage(chatMessage);
		return chatMessage;
	}
	
	@SendTo("/category/msg/{var2}")
	@MessageMapping("/sendImageFile/{var1}/{var2}")
	public ChatMessage sendImageFile(String originFileName,
			@DestinationVariable(value = "var1") int mNum,
			@DestinationVariable(value = "var2") int crNum) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCmContent(originFileName);
		chatMessage.setCmType("img");
		chatMessage.setCrNum(crNum);
		chatMessage.setmNum(mNum);
		cmService.sendChatMessage(chatMessage);		
		return chatMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadImageFile", method = RequestMethod.POST)
	public String uploadImageFile(HttpSession session, MultipartFile uploadimg) {
		String uploadFolder = "/Users/amy/Desktop/test";
		String saveFileName = UUID.randomUUID().toString() + "_" + uploadimg.getOriginalFilename();
		File saveFile = new File(uploadFolder, saveFileName);
		try {
			uploadimg.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String saveFileInfo = "{\"fileName\":\"" + saveFileName + "\",\"originFileName\":\""+ uploadimg.getOriginalFilename() + "\"}";
		return saveFileInfo;
	}
}

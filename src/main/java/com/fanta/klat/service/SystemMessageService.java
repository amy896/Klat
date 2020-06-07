package com.fanta.klat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.model.Member;

@Service
public class SystemMessageService {
	@Autowired
	private ChatMessageService cmService;
	
	public ChatMessage sendEntranceMessage(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCrNum(crNum);
		chatMessage.setmName(member.getmName());
		chatMessage.setCmContent(" 님이 입장하셨습니다.");
		chatMessage.setCmType("system");
		chatMessage.setmNum(member.getmNum());
		int cmNum = cmService.sendChatMessage(chatMessage);
		return cmService.getSystemMessageByCmNum(cmNum);
	}
	
	public ChatMessage sendExitMessage(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCrNum(crNum);
		chatMessage.setmName(member.getmName());
		chatMessage.setCmContent(" 님이 퇴장하셨습니다.");
		chatMessage.setCmType("system");
		chatMessage.setmNum(member.getmNum());
		int cmNum = cmService.sendChatMessage(chatMessage);
		System.out.println("cmNum : "+cmNum);
		return chatMessage;
	}
}

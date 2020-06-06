package com.fanta.klat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.model.Member;

@Service
public class SystemMessageService {
	@Autowired
	private ChatMessageService cmService;
	
	public ChatMessage joinChatRoom(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
//		int cmNum = cmService.addChatMessage(crNum, -1, member.getmName() + " 님이 채팅에 입장하셨습니다.", "systemMsg");
		int cmNum = cmService.sendChatMessage(chatMessage);
		return cmService.getSystemMessageByCmNum(cmNum);
	}
	
	public ChatMessage exitChatRoom(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
//		int cmNum = cmService.addChatMessage(crNum, -1, member.getmName() + " 님이 채팅에서 퇴장하셨습니다.", "systemMsg");
		int cmNum = cmService.sendChatMessage(chatMessage);
		return cmService.getSystemMessageByCmNum(cmNum);
	}
}

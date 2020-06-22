package com.fanta.klat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.model.Member;

@Service
public class SystemMessageService {

	@Autowired
	private ChatMessageService cmService;
	
	//by 혜선, 채팅방 입장 메시지 전송 및 DB 저장 
	public ChatMessage sendEntranceMessage(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCrNum(crNum);
		chatMessage.setCmContent(member.getmName()+" 님이 입장하셨습니다.");
		chatMessage.setCmType("system");
		chatMessage.setCmWriteDate(new Date());
		chatMessage.setmNum(member.getmNum());
		ChatMessage chatMessageSend = cmService.sendChatMessage(chatMessage);
		chatMessageSend = cmService.getChatMessageByCmNum(chatMessageSend.getCmNum());
		return chatMessageSend;
	}
	
	//by 혜선, 채팅방 퇴장 메시지 전송 및 DB 저장
	public ChatMessage sendExitMessage(int crNum, Member member) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setCrNum(crNum);
		chatMessage.setCmContent(member.getmName()+" 님이 퇴장하셨습니다.");
		chatMessage.setCmType("system");
		chatMessage.setCmWriteDate(new Date());
		chatMessage.setmNum(member.getmNum());
		ChatMessage chatMessageSend = cmService.sendChatMessage(chatMessage);
		chatMessageSend = cmService.getChatMessageByCmNum(chatMessageSend.getCmNum());
		return chatMessageSend;
	}
}

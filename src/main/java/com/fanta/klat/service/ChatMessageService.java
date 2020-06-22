package com.fanta.klat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	@Autowired
	private ChatMessageRepository chatMessageRepository;

	//by 혜선, 채팅메시지 전송 및 DB 저장
	public ChatMessage sendChatMessage(ChatMessage chatMessage) {
		ChatMessage chatMessageSaved = chatMessageRepository.saveAndFlush(chatMessage);
		return chatMessageRepository.findById(chatMessageSaved.getCmNum()).get();
	}

	//by 혜선, 채팅메시지 번호(primary key) 기준으로 채팅메시지 가져오기
	public ChatMessage getChatMessageByCmNum(int cmNum) {
		ChatMessage chatMessage = chatMessageRepository.findById(cmNum).get();
		return chatMessage;
	}

	//by 혜선, 채팅방 내 모든 채팅메시지 가져오기
	public List<ChatMessage> getAllChatMessageByCrNum(int crNum) {
		return chatMessageRepository.findByCrNum(crNum);
	}
}
package com.fanta.klat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatMessage;
import com.fanta.klat.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
	@Autowired
	private ChatMessageRepository cmRepository;


	public ChatMessage sendChatMessage(ChatMessage chatMessage) {		
		ChatMessage chatMessageSaved = cmRepository.save(chatMessage);
		return chatMessageSaved;
	}

	public ChatMessage getChatMessageByCmNum(int cmNum) {
		ChatMessage chatMessage = cmRepository.findById(cmNum).get();
		return chatMessage;
	}

	public List<ChatMessage> getAllChatMessageByCrNum(int crNum) {
		return cmRepository.findAll();
	}

	public ChatMessage getSystemMessageByCmNum(int cmNum) {
		return cmRepository.selectSystemMessageByCmNum(cmNum);
	}
}
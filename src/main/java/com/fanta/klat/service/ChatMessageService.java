package com.fanta.klat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.dao.ChatMessageDao;
import com.fanta.klat.model.ChatMessage;

@Service
public class ChatMessageService {
	@Autowired
	private ChatMessageDao cmDao;

	public int sendChatMessage(ChatMessage chatMessage) {		
		int cmNum = 0;
		Date cmWriteDate = new Date();
		chatMessage.setCmWriteDate(cmWriteDate);
		if (cmDao.insertChatMessage(chatMessage) > 0) {
			cmNum = chatMessage.getCmNum();
		}
		return cmNum;
	}

	public ChatMessage getChatMessageByCmNum(int cmNum) {
		return cmDao.selectChatMessageByCmNum(cmNum);
	}

	public List<ChatMessage> getAllChatMessageByCrNum(int crNum) {
		return cmDao.selectAllChatMessageByCrNum(crNum);
	}
}

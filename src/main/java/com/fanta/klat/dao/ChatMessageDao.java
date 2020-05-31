package com.fanta.klat.dao;

import java.util.List;

import com.fanta.klat.model.ChatMessage;

public interface ChatMessageDao {
	public int insertChatMessage(ChatMessage chatMessage);
	public List<ChatMessage> selectAllChatMessageByCrNum(int crNum);
}

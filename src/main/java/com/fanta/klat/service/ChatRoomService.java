package com.fanta.klat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.dao.ChatRoomDao;
import com.fanta.klat.model.ChatRoom;

@Service
public class ChatRoomService {
	@Autowired
	private ChatRoomDao crDao;

	public int addChatRoom(int mNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrTitle(crTitle);

		if (crDao.insertChatRoom(chatRoom) > 0) {
			crDao.insertChatRoomMember(chatRoom.getCrNum(), mNum);
		}
		return chatRoom.getCrNum();
	}
}

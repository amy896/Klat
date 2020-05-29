package com.fanta.klat.service;

import java.util.List;

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

	public boolean modifyChatRoom(int crNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrNum(crNum);
		chatRoom.setCrTitle(crTitle);
		if (crDao.updateChatRoom(chatRoom) > 0) {
			return true;
		}
		return false;
	}

	public boolean exitChatRoom(int crNum, int mNum) {
		if(crDao.deleteChatRoomMember(crNum, mNum) > 0) {
			return true;
		}
		return false; 
	}

	public boolean removeChatRoom(int crNum) {
		if (crDao.deleteChatRoom(crNum) > 0) {
			return true;
		}
		return false;
	}

	public List<ChatRoom> getChatRoomListByMNum(int mNum) {
		return crDao.selectChatRoomListByMNum(mNum);
	}
}

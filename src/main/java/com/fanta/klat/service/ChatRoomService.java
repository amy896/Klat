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
			if(removeChatRoom()) {
				System.out.println("비어있는 채팅방을 삭제했습니다.");
			} else {
				System.out.println("비어있는 채팅방이 없습니다.");
			}
			return true;
		}
		return false; 
	}
	
	public boolean exitAllChatRoom(int mNum) {
		boolean isExit = false;
		List<ChatRoom> chatRoomList = getChatRoomListByMNum(mNum);
		for(int i=0; i<chatRoomList.size(); i++) {
			int crNum = chatRoomList.get(i).getCrNum();
			if(crDao.deleteChatRoomMember(crNum, mNum) > 0) {
				isExit = true;
				if(removeChatRoom()) {
					System.out.println("비어있는 채팅방을 삭제했습니다.");
				} else {
					System.out.println("비어있는 채팅방이 없습니다.");
				}
			}
		}
		return isExit; 
	}

	public boolean removeChatRoom() {
		if (crDao.deleteEmptyChatRoom() > 0) {
			return true;
		}
		return false;
	}

	public List<ChatRoom> getChatRoomListByMNum(int mNum) {
		return crDao.selectChatRoomListByMNum(mNum);
	}
}

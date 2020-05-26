package com.fanta.klat.dao;

import com.fanta.klat.model.ChatRoom;

public interface ChatRoomDao {
	public int insertChatRoom(ChatRoom chatRoom);
	public int insertChatRoomMember(int crNum, int mNum);
}

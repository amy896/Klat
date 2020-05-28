package com.fanta.klat.dao;

import java.util.List;

import com.fanta.klat.model.ChatRoom;

public interface ChatRoomDao {
	public int insertChatRoom(ChatRoom chatRoom);
	public int insertChatRoomMember(int crNum, int mNum);
	public int updateChatRoom(ChatRoom chatRoom);
	public List<ChatRoom> selectChatRoomListByMNum(int mNum);
}

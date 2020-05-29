package com.fanta.klat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fanta.klat.model.ChatRoom;

public interface ChatRoomDao {
	public int insertChatRoom(ChatRoom chatRoom);
	public int insertChatRoomMember(int crNum, int mNum);
	public int updateChatRoom(ChatRoom chatRoom);
	public int deleteChatRoomMember(@Param("crNum")int crNum, @Param("mNum")int mNum);
	public int deleteEmptyChatRoom();
	public List<ChatRoom> selectChatRoomListByMNum(int mNum);
}

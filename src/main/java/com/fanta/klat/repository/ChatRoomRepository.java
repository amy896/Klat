package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.model.ChatRoom;

@Repository("chatRoomRepository")
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

	@Modifying
	@Transactional
	@Query(value = "delete from tbl_chat_room cr where (select count(*) from tbl_chat_room_member crm where crm.cr_num = cr.cr_num) = 0", nativeQuery = true)
	public int eliminateEmptyChatRoom();

	@Query(value = "select cr.cr_num, cr.cr_title "
			+ "from tbl_chat_room cr join tbl_chat_room_member crm " 
			+ "on cr.cr_num = crm.cr_num "
			+ "where crm.m_num = ?1 " 
			+ "order by cr.cr_num desc", nativeQuery = true)
	public List<ChatRoom> selectChatRoomListByMNum(int mNum);
}

package com.fanta.klat.repository;

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

}

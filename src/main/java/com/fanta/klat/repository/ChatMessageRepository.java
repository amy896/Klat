package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.ChatMessage;

@Repository("chatMessageRepository")
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>{

	public List<ChatMessage> findByCrNum(int crNum);
	
	@Query(value = "select cm_num, cm_content, cm_type, cm_write_date, cr_num, m_num\r\n" + 
			"		from tbl_chat_message\r\n" + 
			"		where cm_num = ?1", nativeQuery = true)
	public ChatMessage selectSystemMessageByCmNum(int cmNum);
}
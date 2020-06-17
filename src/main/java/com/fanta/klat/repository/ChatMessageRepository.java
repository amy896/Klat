package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.ChatMessage;

@Repository("chatMessageRepository")
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>{

	@Query(value = "select cm.cm_num, cm.cm_content, cm.cm_type, cm.cm_write_date, cm.cr_num, cm.m_num, m.m_name, m.m_profile_img\r\n" + 
			"		from tbl_chat_message cm, tbl_member m\r\n" + 
			"		where cm.m_num = m.m_num\r\n" + 
			"		and cm.cr_num = ?1", nativeQuery = true)
	public List<ChatMessage> selectAllChatMessageByCrNum(int crNum);	
}
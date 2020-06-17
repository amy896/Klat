package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.model.ChatRoomMember;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Integer> {

	@Query(value = "select m.m_num, m.m_id, m.m_name, m.m_profile_img \n"
			+ "		from tbl_chat_room_member crm join tbl_member m \n"
			+ "		on crm.cr_num = ?1 and crm.m_num = m.m_num and m.m_num != ?2", nativeQuery = true)
	List<ChatRoomMember> findExceptMe(int crNum, int mNum);

	@Transactional
	public int removeBycrNumAndMNum(int crNum, int mNum);
	
	public List<ChatRoomMember> findByCrNum(int crNum);
}

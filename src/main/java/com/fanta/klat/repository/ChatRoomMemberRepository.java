package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.model.ChatRoomMember;

@Repository("chatRoomMemberRepository")
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Integer> {

	@Transactional
	public int removeBycrNumAndMNum(int crNum, int mNum);
	
	public List<ChatRoomMember> findByCrNum(int crNum);

	public List<ChatRoomMember> findByCrNumAndMNumNot(int crNum, int mNum);
	
	public List<ChatRoomMember> findByMNum(int mNum);
}

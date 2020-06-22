package com.fanta.klat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.model.ChatRoom;

@Repository("chatRoomRepository")
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
	
	@Modifying
	@Transactional
	public void removeByCrNum(int crNum);
}

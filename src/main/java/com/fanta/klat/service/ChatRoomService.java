package com.fanta.klat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanta.klat.model.ChatRoom;
import com.fanta.klat.model.ChatRoomMember;
import com.fanta.klat.repository.ChatRoomMemberRepository;
import com.fanta.klat.repository.ChatRoomRepository;

@Service
public class ChatRoomService {
	@Autowired
	private ChatRoomRepository crRepository;
	@Autowired
	private ChatRoomMemberRepository chatRoomMemberRepository;

	public int addChatRoom(int mNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrTitle(crTitle);
		ChatRoom chatRoomSaved = crRepository.save(chatRoom);
		ChatRoomMember chatRoomMember = null;
		if (chatRoomSaved != null) {
			chatRoomMember = new ChatRoomMember();
			chatRoomMember.setCrNum(chatRoomSaved.getCrNum());
			chatRoomMember.setmNum(mNum);
			chatRoomMemberRepository.save(chatRoomMember);
		}
		return chatRoomSaved.getCrNum();
	}

	public boolean addChatRoomMember(int crNum, int mNum) {
		ChatRoomMember chatRoomMember = new ChatRoomMember();
		chatRoomMember.setCrNum(crNum);
		chatRoomMember.setmNum(mNum);
		if (chatRoomMemberRepository.save(chatRoomMember) != null) {
			return true;
		}
		return false;
	}

	public boolean modifyChatRoom(int crNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrNum(crNum);
		chatRoom.setCrTitle(crTitle);

		if (crRepository.save(chatRoom) != null) {
			return true;
		}
		return false;
	}

	public boolean removeChatRoom() {
		if (crRepository.eliminateEmptyChatRoom() > 0) {
			return true;
		}
		return false;
	}

	public boolean exitChatRoom(int crNum, int mNum) {
		if (chatRoomMemberRepository.removeBycrNumAndMNum(crNum, mNum) > 0) {
			if (chatRoomMemberRepository.countByCrNum(crNum)==0) {
				crRepository.removeByCrNum(crNum);
				System.out.println("비어있는 채팅방을 삭제했습니다.");
			} else {
				System.out.println("비어있는 채팅방이 없습니다.");
			}
			return true;
		}
		return false;
	}

	public boolean exitAllChatRoom(int mNum) {
		boolean isExit = false;
		List<ChatRoom> chatRoomList = getChatRoomListByMNum(mNum);

		for (int i = 0; i < chatRoomList.size(); i++) {
			int crNum = chatRoomList.get(i).getCrNum();
			isExit = exitChatRoom(crNum, mNum);
		}
		return isExit;
	}

	public ChatRoom getChatRoomByCrNum(int crNum) {
		ChatRoom chatRoom = crRepository.findById(crNum).get();
		return chatRoom;
	}

	public List<ChatRoom> getChatRoomListByMNum(int mNum) {
		List<ChatRoomMember> chatRoomMemberList = chatRoomMemberRepository.findByMNum(mNum);
		List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
		for (int i = 0; i < chatRoomMemberList.size(); i++) {
			chatRoomList.add(chatRoomMemberList.get(i).getChatRoom());
		}
		return chatRoomList;
	}
}
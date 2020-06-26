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
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private ChatRoomMemberRepository chatRoomMemberRepository;

	//by 혜선, 채팅방 추가하기
	public int addChatRoom(int mNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrTitle(crTitle);
		ChatRoom chatRoomSaved = chatRoomRepository.save(chatRoom);
		ChatRoomMember chatRoomMember = null;
		if (chatRoomSaved != null) {
			chatRoomMember = new ChatRoomMember();
			chatRoomMember.setCrNum(chatRoomSaved.getCrNum());
			chatRoomMember.setmNum(mNum);
			chatRoomMemberRepository.save(chatRoomMember);
		}
		return chatRoomSaved.getCrNum();
	}

	//by 혜선, 채팅방 멤버 추가하기
	public boolean addChatRoomMember(int crNum, int mNum) {
		ChatRoomMember chatRoomMember = new ChatRoomMember();
		chatRoomMember.setCrNum(crNum);
		chatRoomMember.setmNum(mNum);
		if (chatRoomMemberRepository.save(chatRoomMember) != null) {
			return true;
		}
		return false;
	}

	//by 혜선, 채팅방 제목 수정하기
	public boolean modifyChatRoom(int crNum, String crTitle) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setCrNum(crNum);
		chatRoom.setCrTitle(crTitle);

		if (chatRoomRepository.save(chatRoom) != null) {
			return true;
		}
		return false;
	}

	//by 혜선, 채팅방 나가기
	public boolean exitChatRoom(int crNum, int mNum) {
		if (chatRoomMemberRepository.removeByCrNumAndMNum(crNum, mNum) > 0) {
			if (chatRoomMemberRepository.countByCrNum(crNum)==0) {
				chatRoomRepository.removeByCrNum(crNum);
				System.out.println("비어있는 채팅방을 삭제했습니다.");
			} else {
				System.out.println("비어있는 채팅방이 없습니다.");
			}
			return true;
		}
		return false;
	}

	//by 혜선, 모든 채팅방 나가기
	public boolean exitAllChatRoom(int mNum) {
		boolean isExit = false;
		List<ChatRoom> chatRoomList = getChatRoomListByMNum(mNum);

		for (int i = 0; i < chatRoomList.size(); i++) {
			int crNum = chatRoomList.get(i).getCrNum();
			isExit = exitChatRoom(crNum, mNum);
		}
		return isExit;
	}

	//by 혜선, 채팅방 번호(primary key) 기준으로 채팅방 가져오기
	public ChatRoom getChatRoomByCrNum(int crNum) {
		ChatRoom chatRoom = chatRoomRepository.findById(crNum).get();
		return chatRoom;
	}

	//by 혜선, 멤버 번호(mNum) 기준으로 해당 멤버가 속한 모든 채팅방 리스트 가져오기
	public List<ChatRoom> getChatRoomListByMNum(int mNum) {
		List<ChatRoomMember> chatRoomMemberList = chatRoomMemberRepository.findByMNum(mNum);
		List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
		for (int i = 0; i < chatRoomMemberList.size(); i++) {
			chatRoomList.add(chatRoomMemberList.get(i).getChatRoom());
		}
		return chatRoomList;
	}
}
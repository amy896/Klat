package com.fanta.klat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.model.Authority;
import com.fanta.klat.model.ChatRoomMember;
import com.fanta.klat.model.Member;
import com.fanta.klat.repository.AuthorityRepository;
import com.fanta.klat.repository.ChatRoomMemberRepository;
import com.fanta.klat.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberReposiotry;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	ChatRoomMemberRepository chatRoomMemberRepository;
	
	//by미경, 멤버 추가 성공시, 멤버 권한 추가하기
	@Transactional
	public boolean signUpMember(Member member) {
		Authority authority = null;
		if (memberReposiotry.save(member) != null) {
			authority = new Authority();
			authority.setmNum(member.getmNum());
			if (authorityRepository.save(authority) != null) {
				return true;
			}
		}
		return false;
	}

	//by혜선, 
	public void removeMember(int mNum) {
		memberReposiotry.deleteById(mNum);
		authorityRepository.deleteByMNum(mNum);
	}

	//by 미경, 멤버 아이디 기준으로 멤버모델 가져오기
	public Member getMemberByMId(String mId) {
		return memberReposiotry.findByMId(mId);
	}

	//by 미경, 멤버 번호(primary key) 기준으로 멤버모델 가져오기
	public Member getMemberByMNum(int mNum) {
		return memberReposiotry.findById(mNum).get();
	}

	//by 혜선, 
	public boolean modifyMember(Member member) {
		if (memberReposiotry.save(member) != null) {
			return true;
		}
		return false;
	}

	//by 미경, 본인을 제외한 채팅방 참여자 리스트 가져오기
	public List<Member> getChatMemberListExceptMe(int crNum, int mNum) {
		List<ChatRoomMember> chatRoomMemberList = chatRoomMemberRepository.findByCrNumAndMNumNot(crNum, mNum);
		List<Member> memberList = new ArrayList<Member>();
		for (int i = 0; i < chatRoomMemberList.size(); i++) {
			memberList.add(chatRoomMemberList.get(i).getMember());
		}
		return memberList;
	}

	//by 미경, 멤버의 권한 가져오기
	public List<Authority> getAuthoritiesByMNum(int mNum) {
		return authorityRepository.findByMNum(mNum);
	}

	//by 혜선, 
	public List<Member> searchMemberList(String keyword, int crNum, int mNum) {

		List<Member> memberListByKeyword = memberReposiotry.findByMIdContaining(keyword);
		List<ChatRoomMember> memberListInChatRoom = chatRoomMemberRepository.findByCrNum(crNum);

		for (int i = 0; i < memberListInChatRoom.size(); i++) {
			int mNumInChatRoom = memberListInChatRoom.get(i).getmNum();

			for (int j = 0; j < memberListByKeyword.size(); j++) {
				if (memberListByKeyword.get(j).getmNum() == mNumInChatRoom) {
					memberListByKeyword.remove(memberListByKeyword.get(j));
				}
			}
		}

		return memberListByKeyword;
	}
}

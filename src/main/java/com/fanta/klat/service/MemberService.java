package com.fanta.klat.service;

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

	public void removeMember(int mNum) {
		memberReposiotry.deleteById(mNum);
		authorityRepository.deleteByMNum(mNum);
	}

	public Member getMemberByMId(String mId) {
		return memberReposiotry.findByMId(mId);
	}

	public Member getMemberByMNum(int mNum) {
		return memberReposiotry.findById(mNum).get();
	}

	public boolean modifyMember(Member member) {
		if (memberReposiotry.save(member) != null) {
			return true;
		}
		return false;
	}

	public List<ChatRoomMember> getChatMemberListExceptMe(int crNum, int mNum) {
		return chatRoomMemberRepository.findExceptMe(crNum, mNum);
	}

	public List<Authority> getAuthoritiesByMNum(int mNum) {
		return authorityRepository.findByMNum(mNum);
	}

	public List<String> searchMemberList(String keyword, int crNum, int mNum) {
		System.out.println("2-1. " + keyword + " " + crNum + " " + mNum);
		List<String> memberListByKeyword = null;
		//memberReposiotry.findByKeywordLike(keyword);
		System.out.println("2-2. " + memberListByKeyword);
//		      List<Integer> mNumList = chatRoomDao.selectChatRoomMemberListByCrNum(crNum);
		//
//		      memberListByKeyword.remove(memberDao.selectMemberByMNum(mNum).getmId());
		//
//		      for (int j = 0; j < mNumList.size(); j++) {
//		         String mIdInChatRoom = memberDao.selectMemberByMNum(mNumList.get(j)).getmId();
//		         memberListByKeyword.remove(mIdInChatRoom);
//		      }

		return memberListByKeyword;
	}

}

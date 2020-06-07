package com.fanta.klat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanta.klat.dao.ChatRoomDao;
import com.fanta.klat.dao.MemberDao;
import com.fanta.klat.model.Member;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	@Autowired
	ChatRoomDao chatRoomDao;

	@Transactional
	public boolean signUpMember(Member member) {
		if (memberDao.insertMember(member) > 0)
			if (memberDao.insertAuthority(member.getmNum()) > 0)
				return true;
		return false;
	}

	public boolean removeMember(int mNum) {
		if (memberDao.deleteMemberByMNum(mNum) > 0) {
			if (memberDao.deleteAuthority(mNum) > 0) {
				return true;
			}
			return true;
		}
		return false;
	}

	public boolean modifyMember(int mNum, String mName, String mPw) {
		Member member = memberDao.selectMemberByMNum(mNum);
		member.setmName(mName);
		member.setmPw(mPw);
		if (memberDao.updateMember(member) > 0) {
			return true;
		}
		return false;
	}

	public Member getMemberByMId(String mId) {
		return memberDao.selectMemberByMId(mId);
	}

	public Member getMemberByMNum(int mNum) {
		return memberDao.selectMemberByMNum(mNum);
	}

	public List<Member> getChatMemberListExceptMe(int crNum, int mNum) {
		return memberDao.selctChatMemberListExceptMe(crNum, mNum);
	}

	public List<String> getAuthoritiesByMNum(int mNum) {
		return memberDao.selectAuthoritiesByMNum(mNum);
	}

	public List<String> searchMemberList(String keyword, int crNum, int mNum) {
		List<String> memberListByKeyword = memberDao.selectMemberByKeyword(keyword);
		List<Integer> mNumList = chatRoomDao.selectChatRoomMemberListByCrNum(crNum);

		memberListByKeyword.remove(memberDao.selectMemberByMNum(mNum).getmId());

		for (int j = 0; j < mNumList.size(); j++) {
			String mIdInChatRoom = memberDao.selectMemberByMNum(mNumList.get(j)).getmId();
			memberListByKeyword.remove(mIdInChatRoom);
		}

		return memberListByKeyword;
	}
}

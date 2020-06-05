package com.fanta.klat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
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
			return false;
		}
		return false;
	}

	public boolean modifyMember(int mNum, String mName, String mPw, String mProfileImg) {
		Member member = memberDao.selectMemberByMNum(mNum);
		member.setmName(mName);
		member.setmPw(mPw);
		member.setmProfileImg(mProfileImg);
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

	public byte[] getProfileImg(Member member) {
		String path = "/Users/amy/Desktop/profileImage";
		String profileImgName = member.getmProfileImg();
		File file = new File(path + "/" + profileImgName);
		if (!file.exists()) {
			profileImgName = "profileImage.png";
			path = "/Users/amy/workspace1/Klat/src/main/webapp/resources/img";
			file = new File(path + "/" + profileImgName);
		}
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			return IOUtils.toByteArray(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
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

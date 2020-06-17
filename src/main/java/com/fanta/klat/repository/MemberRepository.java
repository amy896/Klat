package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Member findByMId(String mId);

	public List<Member> findByMIdContaining(String mId);
		
	public List<Member> findByMNumIn(List<Integer> mNumList);
}

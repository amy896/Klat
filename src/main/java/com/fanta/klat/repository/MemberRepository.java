package com.fanta.klat.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Member findByMId(String mId);
	
	public List<Member> findByMIdContaining(String mId);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Member findByMId(String mId);

}
>>>>>>> refs/heads/amy

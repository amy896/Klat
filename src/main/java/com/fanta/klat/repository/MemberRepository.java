package com.fanta.klat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Member findByMId(String mId);

}

package com.fanta.klat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanta.klat.model.Authority;

<<<<<<< HEAD
@Repository("authorityRepository")
=======
@Repository
>>>>>>> refs/heads/amy
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	List<Authority> findByMNum(int mNum);

	void deleteByMNum(int mNum);
}

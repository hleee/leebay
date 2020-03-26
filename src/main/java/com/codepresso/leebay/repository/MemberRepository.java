package com.codepresso.leebay.repository;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

	Member findById(long id);
	
	Member findByEmail(String email);
	
	Member findByEmailAndPassword(String email, String password);

}

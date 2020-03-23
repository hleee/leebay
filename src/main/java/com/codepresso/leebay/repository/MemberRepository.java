package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

	List<Member> findById(long id);

}

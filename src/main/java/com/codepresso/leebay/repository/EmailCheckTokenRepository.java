package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.EmailCheckToken;

public interface EmailCheckTokenRepository extends CrudRepository<EmailCheckToken, String> {

	List<EmailCheckToken> findById(long id);

}

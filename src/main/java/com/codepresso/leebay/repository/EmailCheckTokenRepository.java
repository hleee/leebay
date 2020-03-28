package com.codepresso.leebay.repository;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.EmailCheckToken;

public interface EmailCheckTokenRepository extends CrudRepository<EmailCheckToken, String> {

	EmailCheckToken findByEmailCheckToken(String emailCheckToken);

}

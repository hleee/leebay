package com.codepresso.leebay.repository;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.LogInToken;

public interface LogInTokenRepository extends CrudRepository<LogInToken, String> {

	LogInToken findByLogInToken(String logInTokenString);

}

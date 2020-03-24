package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.LogInToken;

public interface LogInTokenRepository extends CrudRepository<LogInToken, String> {

	List<LogInToken> findByLogInToken(String logInToken);

}

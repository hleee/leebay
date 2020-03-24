package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.EmailCheckToken;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Member;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.service.MemberService;

@RestController
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	public MemberService memberService;

	// 이메일 중복 확인
	@PostMapping("/checkEmail")
	public Response insertOneEmailCheckToken(@RequestBody Member enteredEmail) throws Exception {
		EmailCheckToken emailCheckToken = memberService.insertOneEmailCheckToken(enteredEmail);
		Response response = new Response();
		if (emailCheckToken != null) {
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Success");
			response.setData(emailCheckToken);
		} else {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Failure");
			response.setData(emailCheckToken);
		}
		return response;
	}

	// 로그인
	@PostMapping("/logIn")
	public Response insertOneLogInToken(@RequestBody Member member) throws Exception {
		LogInToken logInToken = memberService.insertOneLogInToken(member);
		Response response = new Response();
		if (logInToken == null) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Failure");
			response.setData(logInToken);
		} else {
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Success");
			response.setData(logInToken);
		}
		return response;
	}

	// 회원 가입
	@PostMapping("/signUp")
	public Response insertOneMember(@CookieValue(value = "checktoken", required = false) String emailCheckTokenString,
			@RequestBody Member member) throws Exception {
		Member memberResult = memberService.insertOneMember(emailCheckTokenString, member);
		Response response = new Response();
		if (memberResult == null) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Failure");
			response.setData(memberResult);
		} else {
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Success");
			response.setData(memberResult);
		}
		return response;
	}
}

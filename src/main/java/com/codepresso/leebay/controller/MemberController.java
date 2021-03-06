package com.codepresso.leebay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	public MemberService memberService;

	// 이메일 중복 확인
	@PostMapping("/checkEmail")
	public Response saveEmailCheckToken(@RequestBody Member enteredEmail) throws Exception {
		EmailCheckToken emailCheckToken = memberService.saveEmailCheckToken(enteredEmail);
		Response response = new Response();
		if (emailCheckToken.getEmailCheckToken() == null) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Failure");
			response.setData(emailCheckToken);
		} else {
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Success");
			response.setData(emailCheckToken);
		}
		return response;
	}

	// 로그인
	@PostMapping("/logIn")
	public Response saveLogInToken(@RequestBody Member member) throws Exception {
		LogInToken logInToken = memberService.saveLogInToken(member);
		Response response = new Response();
		if (logInToken.getLogInToken() == null) {
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
	public Response saveMember(@CookieValue(value = "checktoken", required = false) String emailCheckTokenString,
			@RequestBody Member member) throws Exception {
		Member memberResult = memberService.saveMember(emailCheckTokenString, member);
		Response response = new Response();
		if (memberResult.getCreatedAt() == null) {
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

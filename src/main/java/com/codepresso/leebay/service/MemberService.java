package com.codepresso.leebay.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.EmailCheckToken;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Member;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.repository.MemberRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;
import com.codepresso.leebay.util.TokenMaker;

@Service
public class MemberService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public MemberRepository memberRepo;

	@Autowired
	public LogInTokenRepository logInTokenRepo;

	// 이메일 중복 확인
	public EmailCheckToken insertOneEmailCheckToken(Member enteredEmail) throws Exception {
		EmailCheckToken emailCheckToken = new EmailCheckToken();
		Member member = new Member();
		String email = enteredEmail.getEmail();
		member = memberRepo.selectOneMemberByEmail(email);
		if (member == null) {
			String emailCheckTokenString = TokenMaker.makeToken();
			emailCheckToken.setEmail(email);
			emailCheckToken.setEmailCheckToken(emailCheckTokenString);
			logInTokenRepo.insertOneEmailCheckToken(emailCheckToken);
			emailCheckToken = logInTokenRepo.selectOneRowByEmailCheckToken(emailCheckToken);
			return emailCheckToken;
		} else {
			logger.info("Duplicate email found.");
			return emailCheckToken;
		}
	}

	// 로그인
	public LogInToken insertOneLogInToken(Member member) throws Exception {
		LogInToken logInToken = new LogInToken();
		Response response = new Response();
		Member memberInDB = memberRepo.selectOneMemberByEmailAndPassword(member);
		if (memberInDB == null) {
			logger.info("ID-password pair not found.");
			return logInToken;
		} else {
			String email = member.getEmail();
			logInToken.setEmail(email);
			String logInTokenString = TokenMaker.makeToken();
			logInToken.setLogInToken(logInTokenString);
			long memberId = memberInDB.getId();
			logInToken.setMemberId(memberId);
			logInTokenRepo.insertOneLogInToken(logInToken);
			logInToken = logInTokenRepo.selectOneRowByLogInTokenString(logInTokenString);
			return logInToken;
		}
	}

	// 회원 가입
	public Member insertOneMember(String emailCheckTokenString, Member member) throws Exception {
		Response responseVO = new Response();
		Member emptyMember = new Member();
		if (emailCheckTokenString == null) {
			logger.info("Check for email duplication.");
			return emptyMember;
		} else {
			logger.info("Email OK.");
		}
		if (member.getPassword().equals(member.getPasswordReentered())) {
			logger.info("Password OK.");
		} else {
			logger.info("Password doesn't match.");
			return emptyMember;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date todaysDate = new Date();
		String todaysDateFormatted = dateFormat.format(todaysDate);
		int todaysDateInt = Integer.parseInt(todaysDateFormatted);
		String enteredBirthday = member.getBirthday().replaceAll("\\p{Punct}", "");
		int enteredBirthdayInt = Integer.parseInt(enteredBirthday);
		if (todaysDateInt - enteredBirthdayInt < 80000) {
			logger.info("Underage.");
			return emptyMember;
		} else {
			logger.info("Age OK.");
		}
		memberRepo.insertOneMember(member);
		member = memberRepo.selectOneMemberById(member.getId());
		return member;
	}
}

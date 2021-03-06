package com.codepresso.leebay.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codepresso.leebay.domain.EmailCheckToken;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Member;
import com.codepresso.leebay.repository.EmailCheckTokenRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;
import com.codepresso.leebay.repository.MemberRepository;
import com.codepresso.leebay.util.TokenMaker;

@Service
@Transactional
public class MemberService {

	static Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	public MemberRepository memberRepo;

	@Autowired
	public LogInTokenRepository logInTokenRepo;

	@Autowired
	public EmailCheckTokenRepository emailCheckTokenRepo;

	// 이메일 중복 확인
	public EmailCheckToken saveEmailCheckToken(Member enteredEmail) throws Exception {
		EmailCheckToken emailCheckToken = new EmailCheckToken();
		Member member = new Member();
		String email = enteredEmail.getEmail();
		member = memberRepo.findByEmail(email);
		if (member == null) {
			String emailCheckTokenString = TokenMaker.makeToken();
			emailCheckToken.setEmail(email);
			emailCheckToken.setEmailCheckToken(emailCheckTokenString);
			emailCheckTokenRepo.save(emailCheckToken);
			emailCheckToken = emailCheckTokenRepo.findByEmailCheckToken(emailCheckTokenString);
			return emailCheckToken;
		} else {
			logger.info("Duplicate email found.");
			return emailCheckToken;
		}
	}

	// 로그인
	public LogInToken saveLogInToken(Member member) throws Exception {
		LogInToken logInToken = new LogInToken();
		String email = member.getEmail();
		String password = member.getPassword();
		Member memberInDB = memberRepo.findByEmailAndPassword(email, password);
		if (memberInDB == null) {
			logger.info("Email-password pair not found.");
			return logInToken;
		} else {
			logInToken.setEmail(email);
			String logInTokenString = TokenMaker.makeToken();
			logInToken.setLogInToken(logInTokenString);
			long memberId = memberInDB.getId();
			logInToken.setMemberId(memberId);
			logInTokenRepo.save(logInToken);
			logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
			return logInToken;
		}
	}

	// 회원 가입
	public Member saveMember(String emailCheckTokenString, Member member) throws Exception {
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
		Date enteredBirthday = member.getBirthday();
		String enteredBirthdayFormatted = dateFormat.format(enteredBirthday);
		int enteredBirthdayInt = Integer.parseInt(enteredBirthdayFormatted);
		if (todaysDateInt - enteredBirthdayInt < 80000) {
			logger.info("Underage.");
			return emptyMember;
		} else {
			logger.info("Age OK.");
		}
		logger.info("member: " + member);
		memberRepo.save(member);
		member = memberRepo.findById(member.getId());
		return member;
	}
}

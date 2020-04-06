package com.codepresso.leebay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcController {

	static Logger logger = LoggerFactory.getLogger(MvcController.class);

	@GetMapping("/signUp")
	public ModelAndView signUp() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signUp");
		return mav;
	}

	@GetMapping("/logIn")
	public ModelAndView logIn() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("logIn");
		return mav;
	}

	@GetMapping("/main")
	public ModelAndView product(@CookieValue(value = "accesstoken", required = false) String token) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("user", token); // 토큰이 있는 상태를 'user'라고 부르기로 --> ftl에서 조건문에 <#if user??>
		return mav;
	}

	@GetMapping("/main/detail/{productId}")
	public ModelAndView productDetail(@PathVariable("productId") long productId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detail");
		return mav;
	}

}

package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.service.BasketService;

@RestController
@RequestMapping
public class BasketController {

	@Autowired
	public BasketService basketService;

	// 장바구니 조회
	@GetMapping("/basket")
	public ResponseVO selectBasketByMemberID(@CookieValue(value = "accesstoken", required = false) String logInToken)
			throws Exception {
		return basketService.selectBasketByMemberID(logInToken);
	}

	// 장바구니에 추가
	@PostMapping("/basket")
	public ResponseVO insertToBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody Basket basketVO) throws Exception {
		return basketService.insertToBasket(logInToken, basketVO);
	}

	// 장바구니에서 삭제
	@DeleteMapping("/basket")
	public ResponseVO deleteFromBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody Basket basketVO) throws Exception {
		return basketService.deleteFromBasket(logInToken, basketVO);
	}
}

package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.service.BasketService;

@RestController
@RequestMapping
public class BasketController {

	@Autowired
	public BasketService basketService;

	// 장바구니 조회
//	@GetMapping("/basket")
//	public Response findBasketByMemberId(@CookieValue(value = "accesstoken", required = false) String logInToken)
//			throws Exception {
//		Product[] productsInBasketArray = basketService.findBasketByMemberId(logInToken);
//		Response response = new Response();
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Success");
//		response.setData(productsInBasketArray);
//		return response;
//	}

	// 장바구니에 추가
	@PostMapping("/basket")
	public Response saveBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody Basket basket) throws Exception {
		Product product = basketService.saveBasket(logInToken, basket);
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(product);
		return response;
	}

	// 장바구니에서 삭제
	@DeleteMapping("/basket")
	public Response deleteBasket(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestBody Basket basket) throws Exception {
		Product product = basketService.deleteBasket(logInToken, basket);
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(product);
		return response;
	}
}

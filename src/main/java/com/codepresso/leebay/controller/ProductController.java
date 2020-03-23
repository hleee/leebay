package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.service.ProductService;

@RestController
@RequestMapping
public class ProductController {

	@Autowired
	public ProductService productService;

	// 여섯 개씩 조회 (한 페이지 호출)
	@GetMapping("/product")
	public ResponseVO selectSixProducts(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@RequestParam("page") Long page) throws Exception {
		return productService.selectSixProducts(logInToken, page);
	}

	// 상세 조회
	@GetMapping("/product/detail/{productID}")
	public ResponseVO selectOneDetail(@CookieValue(value = "accesstoken", required = false) String logInToken,
			@PathVariable("productID") Long productID) throws Exception {
		return productService.selectOneDetail(logInToken, productID);
	}
}

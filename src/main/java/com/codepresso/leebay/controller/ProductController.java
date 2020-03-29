package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.ProductAndDetail;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.service.ProductService;

@RestController
@RequestMapping
public class ProductController {

	@Autowired
	public ProductService productService;

	// 여섯 개씩 조회 (한 페이지 호출)
	@GetMapping("/product")
	public Response findAllPageable(@CookieValue(value = "accesstoken", required = false) String logInTokenString,
			@RequestParam("page") int page) throws Exception {
		Product[] productPageArray = productService.findAllPageable(logInTokenString, page);
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(productPageArray);
		return response;
	}

	// 상세 조회
	@GetMapping("/product/detail/{productId}")
	public Response findDetailByProductId(@CookieValue(value = "accesstoken", required = false) String logInTokenString,
			@PathVariable("productId") Long productId) throws Exception {
		ProductAndDetail productAndDetail = productService.findDetailByProductId(logInTokenString, productId);
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(productAndDetail);
		return response;
	}
}

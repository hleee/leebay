package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.repository.BasketRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;
import com.codepresso.leebay.repository.ProductRepository;

@Service
public class BasketService {

	static Logger logger = LoggerFactory.getLogger(BasketService.class);

	@Autowired
	public BasketRepository basketRepo;

	@Autowired
	public LogInTokenRepository logInTokenRepo;

	@Autowired
	public ProductRepository productRepo;

	// 장바구니 조회
	public Product[] selectBasketByMemberId(String logInTokenString) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		List<Product> basketList = basketRepo.select(memberId);
		Product[] productsInBasketArray = new Product[basketList.size()];
		for (int i = 0; i < basketList.size(); i++) {
			basketList.get(i).setIsAdded(true);
			productsInBasketArray[i] = basketList.get(i);
		}
		return productsInBasketArray;
	}

	// 장바구니에 추가
	public Product insertToBasket(String logInTokenString, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		basket.setMemberId(memberId);
		long productId = basket.getProductId();
		basketRepo.insertToBasket(basket);
		Product product = productRepo.selectOneProductById(productId);
		product.setIsAdded(true);
		return product;
	}

	// 장바구니에서 삭제
	public Product deleteFromBasket(String logInTokenString, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		basket.setMemberId(memberId);
		long productId = basket.getProductId();
		basketRepo.deleteFromBasket(basket);
		Product product = productRepo.selectOneProductById(productId);
		product.setIsAdded(false);
		return product;
	}
}

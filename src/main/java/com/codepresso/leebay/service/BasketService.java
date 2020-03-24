package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Product;
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
		LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		List<Product> productsInBasketList = productRepo
				.findByIdEqualsBasketProductIdAndBasketMemberIdEqualsMemberIdOrderByBasketIdDesc(memberId);
		Product[] productsInBasketArray = new Product[productsInBasketList.size()];
		for (int i = 0; i < productsInBasketList.size(); i++) {
			productsInBasketList.get(i).setIsAdded(true);
			productsInBasketArray[i] = productsInBasketList.get(i);
		}
		return productsInBasketArray;
	}

	// 장바구니에 추가
	public Product insertToBasket(String logInTokenString, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		basket.setMemberId(memberId);
		long productId = basket.getProductId();
		basketRepo.save(basket);
		Product product = productRepo.findById(productId);
		product.setIsAdded(true);
		return product;
	}

	// 장바구니에서 삭제
	public Product deleteFromBasket(String logInTokenString, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		basket.setMemberId(memberId);
		long productId = basket.getProductId();
		basketRepo.delete(basket);
		Product product = productRepo.findById(productId);
		product.setIsAdded(false);
		return product;
	}
}

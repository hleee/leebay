package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Product[] findBasketByMemberId(String logInTokenString) throws Exception {
		LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		List<Basket> memberBasket = basketRepo.findByMemberId(memberId);
		Product[] productsInBasketArray = new Product[memberBasket.size()];
		for (int i = 0; i < memberBasket.size(); i++) {
			long productId = memberBasket.get(i).getProductId();
			Product product = productRepo.findById(productId);
			product.setIsAdded(true);
			productsInBasketArray[i] = product;
		}
		return productsInBasketArray;
	}

	// 장바구니에 추가
	@Transactional
	public Product saveBasket(String logInTokenString, Basket basket) throws Exception {
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
	@Transactional
	public Product deleteBasket(String logInTokenString, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
		long memberId = logInToken.getMemberId();
		basket.setMemberId(memberId);
		long productId = basket.getProductId();
		basketRepo.deleteByMemberIdAndProductId(memberId, productId);
		Product product = productRepo.findById(productId);
		product.setIsAdded(false);
		return product;
	}
}

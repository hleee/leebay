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
	public Product[] selectBasketByMemberID(String logInToken) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInToken);
		long memberID = logInToken.getMemberID();
		List<Product> basketList = basketRepo.select(memberID);
		Product[] productsInBasketArray = new Product[basketList.size()];
		for (int i = 0; i < basketList.size(); i++) {
			basketList.get(i).setIsAdded(true);
			productsInBasketArray[i] = basketList.get(i);
		}
		return productsInBasketArray;
	}

	// 장바구니에 추가
	public Product insertToBasket(String logInToken, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInToken);
		long memberID = logInToken.getMemberID();
		basket.setMemberID(memberID);
		long productID = basket.getProductID();
		basketRepo.insertToBasket(basket);
		Product product = productRepo.selectOneProductByID(productID);
		product.setIsAdded(true);
		return product;
	}

	// 장바구니에서 삭제
	public Product deleteFromBasket(String logInToken, Basket basket) throws Exception {
		LogInToken logInToken = logInTokenRepo.selectOneRowByLogInToken(logInToken);
		long memberID = logInToken.getMemberID();
		basket.setMemberID(memberID);
		long productID = basket.getProductID();
		basketRepo.deleteFromBasket(basket);
		Product product = productRepo.selectOneProductByID(productID);
		product.setIsAdded(false);
		return product;
	}
}

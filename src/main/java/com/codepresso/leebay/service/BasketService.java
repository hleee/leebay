package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.repository.BasketRepository;
import com.codepresso.leebay.repository.ProductRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;

@Service
public class BasketService {

	static Logger logger = LoggerFactory.getLogger(BasketService.class);

	@Autowired
	public BasketRepository basketDAO;

	@Autowired
	public LogInTokenRepository tokenDAO;

	@Autowired
	public ProductRepository productDAO;

	// 장바구니 조회
	public ResponseVO selectBasketByMemberID(String logInToken) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInToken logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		List<Product> basketList = basketDAO.selectBasketByMemberID(memberID);
		Product[] productsInBasketArray = new Product[basketList.size()];
		for (int i = 0; i < basketList.size(); i++) {
			basketList.get(i).setIsAdded(true);
			productsInBasketArray[i] = basketList.get(i);
		}
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productsInBasketArray);
		return responseVO;
	}

	// 장바구니에 추가
	public ResponseVO insertToBasket(String logInToken, Basket basketVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInToken logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		basketVO.setMemberID(memberID);
		long productID = basketVO.getProductID();
		basketDAO.insertToBasket(basketVO);
		Product productVO = productDAO.selectOneProductByID(productID);
		productVO.setIsAdded(true);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productVO);
		return responseVO;
	}

	// 장바구니에서 삭제
	public ResponseVO deleteFromBasket(String logInToken, Basket basketVO) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		LogInToken logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
		long memberID = logInTokenVO.getMemberID();
		basketVO.setMemberID(memberID);
		long productID = basketVO.getProductID();
		basketDAO.deleteFromBasket(basketVO);
		Product productVO = productDAO.selectOneProductByID(productID);
		productVO.setIsAdded(false);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productVO);
		return responseVO;
	}
}

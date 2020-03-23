package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.Detail;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.ProductAndBasketVO;
import com.codepresso.leebay.domain.ProductAndDetailVO;
import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.repository.BasketRepository;
import com.codepresso.leebay.repository.MemberRepository;
import com.codepresso.leebay.repository.ProductRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;

@Service
public class ProductService {

	static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	public MemberRepository memberDAO;

	@Autowired
	public ProductRepository productDAO;

	@Autowired
	public LogInTokenRepository tokenDAO;

	@Autowired
	public BasketRepository basketDAO;

	// 여섯 개씩 조회 (한 페이지 호출)
	public ResponseVO selectSixProducts(String logInToken, long page) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		long offsetValue = (page - 1) * 6;
		List<Product> tokenNullProductVOList = productDAO.selectSixProducts(offsetValue);
		if (logInToken == null) {
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(tokenNullProductVOList);
		} else {
			ProductAndBasketVO productAndBasketVO = new ProductAndBasketVO();
			LogInToken logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			long memberID = logInTokenVO.getMemberID();
			productAndBasketVO.setOffsetValue(offsetValue);
			productAndBasketVO.setMemberID(memberID);
			List<Product> tokenNotNullProductVOList = productDAO.selectSixProductsWithBasketInfo(productAndBasketVO);
			responseVO.setCode(HttpStatus.OK.value());
			responseVO.setMessage("Success");
			responseVO.setData(tokenNotNullProductVOList);
		}
		return responseVO;
	}

	// 상세 조회
	public ResponseVO selectOneDetail(String logInToken, long productID) throws Exception {
		ResponseVO responseVO = new ResponseVO();
		Basket basketVO = new Basket();
		LogInToken logInTokenVO = new LogInToken();
		Product productVO = productDAO.selectOneProductByID(productID);
		ProductAndDetailVO productAndDetailVO = new ProductAndDetailVO();
		productAndDetailVO.setId(productVO.getId());
		productAndDetailVO.setName(productVO.getName());
		productAndDetailVO.setImage(productVO.getImage());
		productAndDetailVO.setDescription(productVO.getDescription());
		productAndDetailVO.setOriginalPrice(productVO.getOriginalPrice());
		productAndDetailVO.setDiscountedPrice(productVO.getDiscountedPrice());
		productAndDetailVO.setCreatedAt(productVO.getCreatedAt());
		if (logInToken == null) {
			productAndDetailVO.setIsAdded(null);
		} else {
			logInTokenVO = tokenDAO.selectOneRowByLogInToken(logInToken);
			long memberID = logInTokenVO.getMemberID();
			basketVO.setMemberID(memberID);
			basketVO.setProductID(productID);
			basketVO = basketDAO.selectBasketByMemberIDAndProductID(basketVO);
			if (basketVO != null) {
				productAndDetailVO.setIsAdded(true);
			} else {
				productAndDetailVO.setIsAdded(false);
			}
		}
		List<Detail> detailVOList = productDAO.selectAllDetails(productID);
		productAndDetailVO.setDetail(detailVOList);
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(productAndDetailVO);
		return responseVO;
	}
}

package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Basket;
import com.codepresso.leebay.domain.Detail;
import com.codepresso.leebay.domain.LogInToken;
import com.codepresso.leebay.domain.Product;
import com.codepresso.leebay.domain.ProductAndDetail;
import com.codepresso.leebay.repository.BasketRepository;
import com.codepresso.leebay.repository.DetailRepository;
import com.codepresso.leebay.repository.LogInTokenRepository;
import com.codepresso.leebay.repository.MemberRepository;
import com.codepresso.leebay.repository.ProductRepository;

@Service
public class ProductService {

	static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	public MemberRepository memberRepo;

	@Autowired
	public ProductRepository productRepo;

	@Autowired
	public LogInTokenRepository logInTokenRepo;

	@Autowired
	public BasketRepository basketRepo;

	@Autowired
	public DetailRepository detailRepo;

	// 여섯 개씩 조회 (한 페이지 호출)
	public Page<Product> findAllPageable(String logInTokenString, int page) throws Exception {
		Pageable paging = PageRequest.of(page - 1, 6, Sort.Direction.DESC, "id");
		if (logInTokenString == null) {
			return productRepo.findAll(paging);
		} else {
			LogInToken logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
			long memberId = logInToken.getMemberId();
			return productRepo.findAllByMemberIdAndPage(memberId, paging);
		}
	}

	// 상세 조회
	public ProductAndDetail findDetailByProductId(String logInTokenString, long productId) throws Exception {
		Basket basket = new Basket();
		LogInToken logInToken = new LogInToken();
		Product product = productRepo.findById(productId);
		ProductAndDetail productAndDetail = new ProductAndDetail();
		productAndDetail.setId(product.getId());
		productAndDetail.setName(product.getName());
		productAndDetail.setImage(product.getImage());
		productAndDetail.setDescription(product.getDescription());
		productAndDetail.setOriginalPrice(product.getOriginalPrice());
		productAndDetail.setDiscountedPrice(product.getDiscountedPrice());
		productAndDetail.setCreatedAt(product.getCreatedAt());
		if (logInTokenString == null) {
			productAndDetail.setIsAdded(null);
		} else {
			logInToken = logInTokenRepo.findByLogInToken(logInTokenString);
			long memberId = logInToken.getMemberId();
			basket.setMemberId(memberId);
			basket.setProductId(productId);
			basket = basketRepo.findByMemberIdAndProductId(basket);
			if (basket != null) {
				productAndDetail.setIsAdded(true);
			} else {
				productAndDetail.setIsAdded(false);
			}
		}
		List<Detail> detailList = detailRepo.findByProductId(productId);
		productAndDetail.setDetail(detailList);
		return productAndDetail;
	}
}

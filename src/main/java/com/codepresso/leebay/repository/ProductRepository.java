package com.codepresso.leebay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findById(long id);

//	List<Product> findByIdEqualsBasketProductIdAndBasketMemberIdEqualsMemberIdOrderByBasketIdDesc(long memberId);

	Page<Product> findAll(Pageable paging);
	
}

package com.codepresso.leebay.repository;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {

	Basket findByMemberIdAndProductId(Basket basket);

}

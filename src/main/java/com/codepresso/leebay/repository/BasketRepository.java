package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {

	Basket findByMemberIdAndProductId(long memberId, long productId);

	long deleteByMemberIdAndProductId(long memberId, long productId);

	List<Basket> findByMemberId(Long memberId);
}

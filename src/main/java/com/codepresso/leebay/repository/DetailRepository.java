package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Detail;

public interface DetailRepository extends CrudRepository<Detail, Long> {

	List<Detail> findByProductIdOrderByIdDesc(long productId);

}

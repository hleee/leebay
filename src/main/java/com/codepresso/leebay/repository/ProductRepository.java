package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findById(long id);

}

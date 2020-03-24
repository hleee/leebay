package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Banner;

public interface BannerRepository extends CrudRepository<Banner, Long> {

	List<Banner> findFirst5ByIdOrderByIdDesc();

}

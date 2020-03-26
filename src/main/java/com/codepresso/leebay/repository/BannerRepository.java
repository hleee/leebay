package com.codepresso.leebay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Banner;

public interface BannerRepository extends CrudRepository<Banner, Long> {

	Page<Banner> findAll(Pageable paging);

}

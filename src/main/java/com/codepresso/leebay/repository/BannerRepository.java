package com.codepresso.leebay.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codepresso.leebay.domain.Banner;

public interface BannerRepository extends CrudRepository<Banner, Long> {

	List<Banner> findByContent(String content);

	@Query("SELECT b FROM Banner b ORDER BY b.id DESC")
	List<Banner> queryAnnotationTest4(Pageable paging);

}

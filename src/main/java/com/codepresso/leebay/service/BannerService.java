package com.codepresso.leebay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Banner;
import com.codepresso.leebay.repository.BannerRepository;

@Service
public class BannerService {

	static Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Autowired
	public BannerRepository bannerRepo;

	public Page<Banner> findAllPageable() throws Exception {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
		return bannerRepo.findAll(paging);
	}
}
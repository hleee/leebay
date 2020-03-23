package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.service.BannerService;

@RestController
@RequestMapping
public class BannerController {

	@Autowired
	public BannerService bannerService;

	@GetMapping
	public ResponseVO selectFiveLatestBanners() throws Exception {
		return bannerService.selectFiveLatestBanners();
	}
}

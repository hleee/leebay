package com.codepresso.leebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.leebay.domain.Banner;
import com.codepresso.leebay.domain.Response;
import com.codepresso.leebay.service.BannerService;

@RestController
@RequestMapping
public class BannerController {

	@Autowired
	public BannerService bannerService;

	@GetMapping
	public Response selectFiveLatestBanners() throws Exception {
		Banner[] bannerArray = bannerService.selectFiveLatestBanners();
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(bannerArray);
		return response;
	}
}

package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Banner;
import com.codepresso.leebay.repository.BannerRepository;

@Service
public class BannerService {

	static Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Autowired
	public BannerRepository bannerRepo;

	public Banner[] selectFiveLatestBanners() throws Exception {
		List<Banner> bannerList = bannerRepo.selectFiveLatestBanners();
		Banner[] bannerArray = new Banner[bannerList.size()];
		for (int i = 0; i < bannerList.size(); i++) {
			bannerArray[i] = bannerList.get(i);
		}
		return bannerArray;
	}
}
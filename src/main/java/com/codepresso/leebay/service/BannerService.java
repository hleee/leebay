package com.codepresso.leebay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codepresso.leebay.domain.Banner;
import com.codepresso.leebay.domain.ResponseVO;
import com.codepresso.leebay.repository.BannerRepository;

@Service
public class BannerService {

	static Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Autowired
	public BannerRepository bannerDAO;

	public ResponseVO selectFiveLatestBanners() throws Exception {
		ResponseVO responseVO = new ResponseVO();
		List<Banner> bannerVOList = bannerDAO.selectFiveLatestBanners();
		Banner[] bannerVOArray = new Banner[bannerVOList.size()];
		for (int i = 0; i < bannerVOList.size(); i++) {
			bannerVOArray[i] = bannerVOList.get(i);
		}
		responseVO.setCode(HttpStatus.OK.value());
		responseVO.setMessage("Success");
		responseVO.setData(bannerVOArray);
		return responseVO;
	}
}
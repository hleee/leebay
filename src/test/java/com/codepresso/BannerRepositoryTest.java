package com.codepresso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codepresso.leebay.domain.Banner;
import com.codepresso.leebay.repository.BannerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerRepositoryTest {

	static Logger logger = LoggerFactory.getLogger(BannerRepositoryTest.class);

	@Autowired
	private BannerRepository bannerRepo;

	// Create
	@Test
	public void testInsertBanner() {
		Banner banner = new Banner();
		banner.setContent("All books off 50%");
		bannerRepo.save(banner);
	}

	// Read
	@Test
	public void testGetBanner() {
		Banner banner = bannerRepo.findById(1L).get();
		logger.info(banner.toString());
	}

	// Update
	@Test
	public void testUpdateBanner() {
		logger.info("=== SELECT FIRST BANNER ===");
		Banner banner = bannerRepo.findById(1L).get();
		logger.info("=== UPDATE CONTENT ===");
		banner.setContent("Content edited: all shoes off 50%");
		bannerRepo.save(banner);
	}

	// Delete
	@Test
	public void testDeleteBanner() {
		bannerRepo.deleteById(1L);
	}
}

package com.squapl.sa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableCaching
public class StoneappleApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(StoneappleApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(StoneappleApplication.class, args);
		
		
		logger.error("Message logged at ERROR level");
		logger.warn("Message logged at warn level");
		logger.info("Message logged at info level");
		
	}
	
	
	
//	@Bean
//	public CacheManager cacheManager() {
//		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("articles");
//		return cacheManager;
//		
//		
//	}
}

package com.harini.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
	
	Logger logger = LoggerFactory.getLogger(GreetingsController.class);
	
	// http://localhost:8080/greetings
	@GetMapping("/greetings")
	public String greetings() {
		logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
		return "Welcome to Spring boot Webproject ";
		
	}
}
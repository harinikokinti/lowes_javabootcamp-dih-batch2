package com.lowes.empapp;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Externalize Configuration:
Spring Boot lets you externalize your configuration so that you can work with the same 
application code in different environments. 
You can use properties files, YAML files, environment variables, and command-line arguments 
to externalize configuration. 
Property values can be injected directly into your beans by using the @Value annotation,
accessed through Spring’s Environment abstraction, or be bound to structured objects 
through @ConfigurationProperties
 */

@SpringBootApplication
public class EmployeeMgmtWithSpringBootApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(EmployeeMgmtWithSpringBootApplication.class);
	
	@Value("${application.name}")
	private String applicationName;
	
	@Value("${username}")
	private String username;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgmtWithSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("My Application name:{} , Username:{}",applicationName,username);
		System.out.println("My Application name: " + applicationName + " Username:"  + username);
		
		logger.debug("This is DEBUG");
		logger.error("This is ERROR");
		logger.trace("This is TRACE");
		logger.warn("This is WARN");
		logger.info("This is INFO");
	
	}

}

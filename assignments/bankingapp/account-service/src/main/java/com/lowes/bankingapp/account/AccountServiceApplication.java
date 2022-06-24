package com.lowes.bankingapp.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AccountServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceApplication.class);

//	@Value("${application.name}")
//	private String applicationName;

	@Value("${username}")
	private String username;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		// logger.info("My Application name:{} , Username:{}", applicationName,
		// username);
		// System.out.println("My Application name: " + applicationName + " Username:" +
		// username);

	}

}

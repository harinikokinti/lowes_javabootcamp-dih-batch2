package com.lowes.bankingapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class BankingAppGateway {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppGateway.class, args);
	}

}

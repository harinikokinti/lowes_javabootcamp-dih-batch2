package com.lowes.bankingapp.fundtransfer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

import com.lowes.bankingapp.fundtransfer.model.FundTransfer;

@Configuration
public class FundTransferConfig {
	// create Bean for Rest Template to auto wire the Rest Template object.
	@Autowired
	@Bean
	public RestTemplate accountRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();

	}

}

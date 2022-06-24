package com.lowes.bankingapp.fundtransfer.controller;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.fundtransfer.model.FundTransfer;
import com.lowes.bankingapp.fundtransfer.model.ResponseMessage;
import com.lowes.bankingapp.fundtransfer.service.FundTransferService;

@RestController
public class FundTransferController {

	Logger logger = LoggerFactory.getLogger(FundTransferController.class);

	@Autowired
	FundTransferService fundTransferService;

	// Create Account
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody FundTransfer fundTransfer)
			throws URISyntaxException {
		fundTransferService.create(fundTransfer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		ResponseMessage response = new ResponseMessage("Success", "Fund Transfer done successfully");
		return ResponseEntity.created(location).body(response);
	}
}

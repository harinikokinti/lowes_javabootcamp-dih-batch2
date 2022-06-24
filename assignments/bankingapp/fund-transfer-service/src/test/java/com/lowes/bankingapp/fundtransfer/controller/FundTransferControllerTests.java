package com.lowes.bankingapp.fundtransfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.lowes.bankingapp.fundtransfer.model.FundTransfer;
import com.lowes.bankingapp.fundtransfer.model.ResponseMessage;
import com.lowes.bankingapp.fundtransfer.service.FundTransferService;

//API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FundTransferControllerTests {
	
	@Autowired
	TestRestTemplate restTemp;

	// @Autowired
	@MockBean
	FundTransferService fundTransferService;
	
	List<FundTransfer> fundTransfers = new ArrayList<>();
	
	
	@BeforeEach
	public void setup() {
		// Initialize Test data
		FundTransfer fundTransfer1 = new FundTransfer();
		fundTransfer1.setSourceAccId("1");
		fundTransfer1.setTargetAccId("2");
		fundTransfer1.setDescription("rent amount");
		fundTransfer1.setAmount(15000);
		// fundTransferService.create(fundTransfer1);
		fundTransfers.add(fundTransfer1);

		FundTransfer fundTransfer2 = new FundTransfer();
		fundTransfer2.setSourceAccId("1");
		fundTransfer2.setTargetAccId("2");
		fundTransfer2.setDescription("rent amount");
		fundTransfer2.setAmount(15000);
		// fundTransferService.create(fundTransfer1);
		fundTransfers.add(fundTransfer2);
	}

	@AfterEach
	public void cleanup() {
		// accountService.clear();
		fundTransfers.clear();
	}
	
	
	@Test
	public void shouldCreateFundTransfer() throws URISyntaxException {	
		
		String reqBody = "{\"sourceAccId\":\"300\",\"targetAccId\":\"2\",\"description\":\"Savings\",\"amount\":50000}";
		
		// Step 1: Create Request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));

		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);
		//System.out.println("Response: " + response.getBody());
		//Response: {"status":"Success","message":"Fund Transfer done successfully"}
		
		// Step 4: Validate the Response		
		ResponseMessage responseMsg = response.getBody();
		assertEquals("Success", responseMsg.getStatus());
		assertEquals("Fund Transfer done successfully",responseMsg.getMessage());

	}
	
	
	
	@Test
	public void shouldThrowExceptionWhenNotPassingMandatoryDetails() throws URISyntaxException {
		
		//String reqBody = "{\"sourceAccId\":\"1\",\"targetAccId\":\"2\",\"description\":\"Savings\",\"amount\":50000}";
		
		String reqBody = "{\"sourceAccId\":\"\",\"targetAccId\":\"2\",\"description\":\"Savings\",\"amount\":50000}";

		// Step 1: Create Request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));

		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);
		System.out.println("Response: " + response.getBody());

		// Step 4: Validate the Response		
		ResponseMessage responseMsg = response.getBody();		
		assertEquals("Failure: No proper method arguments", responseMsg.getStatus());
		assertEquals("sourceAccId cannot be blank",responseMsg.getErrors().get(0));

	}

}

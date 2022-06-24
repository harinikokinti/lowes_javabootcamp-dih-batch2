package com.lowes.bankingapp.fundtransfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import com.lowes.bankingapp.fundtransfer.exception.InsufficientBalanceException;
import com.lowes.bankingapp.fundtransfer.model.Account;
import com.lowes.bankingapp.fundtransfer.model.FundTransfer;

@SpringBootTest
public class FundTransferServiceTests {

	@Autowired
	FundTransferService fundTransferService;

	@MockBean
	private RestTemplate restTemplate;
	
	@MockBean
	KafkaTemplate<String, String> kafkaTemplate;

	@BeforeAll
	public static void init() {
		// Logic to initialize test data goes here
		System.out.println("Test data initialization at class level..");
	}

	@AfterAll
	public static void tearDown() {
		// Logic to clean up test data goes here
		System.out.println("Test data clean up at class level..");
	}

	private static List<FundTransfer> fundTransfers = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initialize Test data
		FundTransfer fundTransfer1 = new FundTransfer();
		fundTransfer1.setSourceAccId("1");
		fundTransfer1.setTargetAccId("2");
		fundTransfer1.setDescription("school fees");
		fundTransfer1.setAmount(20000);
		// fundTransferService.create(FundTransfer1);
		fundTransfers.add(fundTransfer1);

		FundTransfer fundTransfer2 = new FundTransfer();
		fundTransfer2.setSourceAccId("2");
		fundTransfer2.setTargetAccId("3");
		fundTransfer2.setDescription("college fees");
		fundTransfer2.setAmount(50000);
		// fundTransferService.create(FundTransfer2);
		fundTransfers.add(fundTransfer2);
	}

	@AfterEach
	public void cleanup() {
		// fundTransferService.clear();
		fundTransfers.clear();
	}

	@Test
	public void shouldCreateFundTransferWhenPassingFundTransferDetails() { // test create in FundTransfer service
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setSourceAccId("3");
		fundTransfer.setTargetAccId("4");
		fundTransfer.setDescription("grocery fees");
		fundTransfer.setAmount(5000);
		
		Account sourceAccount = new Account();
		sourceAccount.setId(3);
		sourceAccount.setStatus("active");
		sourceAccount.setBalance(10000); 

		ResponseEntity<Account> response = new ResponseEntity<Account>(sourceAccount, HttpStatus.OK);
		Mockito.when(restTemplate.<Account>getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(response);
		Mockito.when(kafkaTemplate.send(Mockito.anyString(),Mockito.anyString())).thenReturn(null);
		FundTransfer fundTransferCreated = fundTransferService.create(fundTransfer);
		assertNotNull(fundTransferCreated);
		assertEquals(fundTransfer.getSourceAccId(), fundTransferCreated.getSourceAccId());

	}

	@Test
	public void shouldThrowExceptionWhenSourceAccountHasInsufficientBalance() {

		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setSourceAccId("3");
		fundTransfer.setTargetAccId("4");
		fundTransfer.setDescription("grocery fees");
		fundTransfer.setAmount(5000);

		Account sourceAccount = new Account();
		sourceAccount.setId(3);
		sourceAccount.setStatus("active");
		sourceAccount.setBalance(0); // send insufficient balance 0 or less than 5000

		ResponseEntity<Account> response = new ResponseEntity<Account>(sourceAccount, HttpStatus.OK);
		Mockito.when(restTemplate.<Account>getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(response);
		assertThrows(InsufficientBalanceException.class, () -> fundTransferService.create(fundTransfer));

	}

}

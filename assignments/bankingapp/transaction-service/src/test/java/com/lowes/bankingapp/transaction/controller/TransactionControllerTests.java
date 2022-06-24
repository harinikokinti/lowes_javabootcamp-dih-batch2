package com.lowes.bankingapp.transaction.controller;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.lowes.bankingapp.transaction.model.Transaction;
import com.lowes.bankingapp.transaction.service.TransactionService;

//API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTests {
	@Autowired
	TestRestTemplate restTemp;

	// @Autowired
	@MockBean
	TransactionService transactionService;

	private static List<Transaction> transactions = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initialize Test data
		Transaction transaction1 = new Transaction();
		transaction1.setId(1);
		transaction1.setAccountId(1);
		transaction1.setDescription("school fees");
		transaction1.setType("credit");
		transaction1.setAmount(5000);
		// transactionService.create(transaction1);
		transactions.add(transaction1);

		Transaction transaction2 = new Transaction();
		transaction2.setId(2);
		transaction2.setAccountId(2);
		transaction2.setDescription("school fees");
		transaction2.setType("debit");
		transaction2.setAmount(5000);
		// transactionService.create(transaction1);
		transactions.add(transaction2);
	}

	@AfterEach
	public void cleanup() {
		// transactionService.clear();
//		transactions.clear();
	}

	@Test // to return all transactions from controller
	public void shouldReturnAllTransactions() {

		Mockito.when(transactionService.getAll()).thenReturn(transactions);

		// REST Template
		// Step 1: Create Request
		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		ResponseEntity<Object> response = restTemp.getForEntity("/", Object.class);

		List<Transaction> transactions = (List) response.getBody();

		System.out.println("Response: " + transactions);

		// Step 4: Validate the Response
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(transactions.size()).isEqualTo(2);

	}
}

package com.lowes.bankingapp.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.model.ResponseMessage;
import com.lowes.bankingapp.account.service.AccountService;

// API Test / Integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTests {

	@Autowired
	TestRestTemplate restTemp;

	// @Autowired
	@MockBean
	AccountService accountService;

	private static List<Account> accounts = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initialize Test data
		Account account1 = new Account();
		account1.setId(1);
		account1.setName("Vamsi");
		account1.setStatus("active");
		account1.setType("savings");
		account1.setBalance(50000);
		// accountService.create(account1);
		accounts.add(account1);

		Account account2 = new Account();
		account2.setId(1);
		account2.setName("Radha");
		account2.setStatus("active");
		account2.setType("current");
		account2.setBalance(60000);
		// accountService.create(account2);
		accounts.add(account2);
	}

	@AfterEach
	public void cleanup() {
		// accountService.clear();
		accounts.clear();
	}

	@Test
	public void shouldCreateAccount() throws URISyntaxException {
		Account account = new Account();
		account.setId(1);
		account.setName("Ramya");
		account.setType("savings");
		account.setStatus("active");
		account.setBalance(50000);
		Mockito.when(accountService.create(Mockito.any(Account.class))).thenReturn(account);

		String reqBody = "{\"id\":\"1\",\"name\":\"Ramya\",\"type\":\"savings\",\"status\":\"active\",\"balance\":\"50000\"}";

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
		assertEquals("Success", responseMsg.getStatus());
		assertEquals("Account created successfully", responseMsg.getMessage());
		Assertions.assertThat(response).isNotNull();
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test // to return all accounts from controller
	public void shouldReturnAllAccounts() {

		Mockito.when(accountService.getAll()).thenReturn(accounts);

		// REST Template
		// Step 1: Create Request
		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		ResponseEntity<Object> response = restTemp.getForEntity("/", Object.class);

		List<Account> accounts = (List) response.getBody();

		System.out.println("Response: " + accounts);

		// Step 4: Validate the Response
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(accounts.size()).isEqualTo(2);

	}

	@Test // test Mandatory fields
	public void shouldThrowExceptionWhenNotPassingMandatoryDetails() throws URISyntaxException {

		// String reqBody =
		// "{\"id\":\"1\",\"name\":\"Ramya\",\"type\":\"savings\",\"status\":\"active\","
		// + "\"balance\":\"50000\"}"; // with name to fail the test case

		String reqBody = "{\"id\":\"1\",\"name\":\"\",\"type\":\"savings\",\"status\":\"active\","
				+ "\"balance\":\"50000\"}";

		// Step 1: Create Request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));

		// Response:{"status":"Failure: No proper method arguments","errors":["name
		// cannot be blank"]}
		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);

		ResponseMessage responseMsg = response.getBody();
		assertEquals("Failure: No proper method arguments", responseMsg.getStatus());
		assertEquals("name cannot be blank", responseMsg.getErrors().get(0));
	}

}

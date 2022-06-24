package com.lowes.bankingapp.account.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.lowes.bankingapp.account.exception.AccountNotFoundException;
import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.repository.AccountRepository;

@SpringBootTest
public class AccountServiceTests {

	@Autowired
	AccountService accountService;

	@MockBean
	AccountRepository accountRepo;

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
//		accounts.clear();
	}

	@Test
	public void shouldCreateAccountWhenPassingAccountDetails() { // test create in account service
		Account account = new Account();
		account.setId(1);
		account.setName("Ramya");
		account.setType("savings");
		Mockito.when(accountRepo.save(Mockito.any(Account.class))).thenReturn(account);
		Account accountCreated = accountService.create(account);
		assertNotNull(accountCreated);
		assertEquals(account.getName(), accountCreated.getName());

	}

	@Test
	public void shouldGetAccountWhenPassingAccountIdDetails() { // test get account when passing id
		Account account = new Account();
		account.setId(1);
		account.setName("Ramya");
		account.setType("savings");
		Mockito.when(accountRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(account));
		Account accountReturned = accountService.get(1);
		assertNotNull(accountReturned);
		assertEquals(account.getName(), accountReturned.getName());
		assertEquals(account.getType(), accountReturned.getType());
	}

	@Test
	public void shouldUpdateAccountForGivenAccountId() { // test update account
		Account account = new Account();
		account.setId(2);
		account.setName("Seetha");
		account.setType("current");
		account.setStatus("active");
		account.setBalance(50000);

		Account accountNew = new Account();
		accountNew.setId(2);
		accountNew.setName("Seetha");
		accountNew.setType("savings");
		accountNew.setStatus("active");
		accountNew.setBalance(70000);

		Mockito.when(accountRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(account));

		Mockito.when(accountRepo.save(Mockito.any(Account.class))).thenReturn(accountNew);

		Account accountUpdated = accountService.update(2, accountNew);
		accountUpdated.setName("Lakshmi");

		assertNotNull(accountUpdated);
		assertNotEquals(account.getBalance(), accountUpdated.getBalance());

	}

	@Test
	public void shouldDeleteAccountWhenPassingValidAccountId() { // test delete operation
		Mockito.doNothing().when(accountRepo).deleteById(Mockito.anyInt()); // expectations on void method
		assertDoesNotThrow(() -> accountService.delete(3));

		Mockito.doThrow(EmptyResultDataAccessException.class).when(accountRepo).deleteById(Mockito.anyInt());
		assertThrows(AccountNotFoundException.class, () -> accountService.delete(3));
	}

	@Test
	public void shouldGetAccountExceptionWhenAccountNotFound() { // test Account not found exception
		Mockito.when(accountRepo.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
		assertThrows(AccountNotFoundException.class, () -> accountService.get(1));

		// to fail
//		Account account = new Account();
//		account.setId(1);
//		account.setName("Ramya");
//		account.setType("savings");
//
//		Mockito.when(accountRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(account));
//		assertThrows(AccountNotFoundException.class, () -> accountService.get(1));

	}
}

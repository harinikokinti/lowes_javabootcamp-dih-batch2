package com.lowes.bankingapp.transaction.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
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
import com.lowes.bankingapp.transaction.exception.TransactionNotFoundException;
import com.lowes.bankingapp.transaction.model.Transaction;
import com.lowes.bankingapp.transaction.repository.TransactionRepository;

@SpringBootTest
public class TransactionServiceTests {

	@Autowired
	TransactionService transactionService;

	@MockBean
	TransactionRepository transactionRepo;

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

	@Test
	public void shouldGetTransactionWhenPassingTrasactionIdDetails() { // test get transaction when passing id
		Transaction transaction = new Transaction();
		transaction.setId(1);
		transaction.setAccountId(2);
		transaction.setDescription("college fees");
		transaction.setType("credit");
		transaction.setAmount(5000);
		Mockito.when(transactionRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(transaction));
		Transaction transactionReturned = transactionService.get(1);
		assertNotNull(transactionReturned);
		assertEquals(2, transactionReturned.getAccountId());
	}

	@Test
	public void shouldDeleteTransactionWhenPassingValidTransactionId() { // test delete operation
		Mockito.doNothing().when(transactionRepo).deleteById(Mockito.anyInt()); // expectations on void method
		assertDoesNotThrow(() -> transactionService.delete(3));

		Mockito.doThrow(EmptyResultDataAccessException.class).when(transactionRepo).deleteById(Mockito.anyInt());
		assertThrows(TransactionNotFoundException.class, () -> transactionService.delete(3));
	}

	@Test
	public void shouldGetTransactionExceptionWhenTransactionNotFound() { // test Transaction not found exception
		Mockito.when(transactionRepo.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
		assertThrows(TransactionNotFoundException.class, () -> transactionService.get(1));
	}

	@Test
	public void shouldCreateTransactionWhenListenFundTransferCreatedMethodInvoked() throws Exception {			
		String value = "1,2,5000,collegefees";
		ConsumerRecord<String, String> consumerRecord = new ConsumerRecord<String, String>(
				"test-topic", 1, 3434, "test-key", value);	
		transactionService.listenFundTransferCreated(consumerRecord);
		
		/*		
		Since listenFundTransferCreated returns void,		
		check save method if the method is called 2 times for credit & debit transactions
		*/	
		Mockito.verify(transactionRepo, Mockito.times(2)).save(Mockito.any()); 
	}
	
	
	@Test
	public void shouldCreateRollBackTransactionWhenListenRollbackFromAccountMethodInvoked() throws Exception {			
		String value = "1,2,5000,collegefees";
		ConsumerRecord<String, String> consumerRecord = new ConsumerRecord<String, String>(
				"test-topic", 1, 3434, "test-key", value);
			
		transactionService.listenRollbackFromAccount(consumerRecord);
		
		/*		
		Since listenRollbackFromAccount returns void,		
		check save method if the method is called 2 times for rollback credit & debit transactions
		*/	
		Mockito.verify(transactionRepo, Mockito.times(2)).save(Mockito.any()); 

	}
}

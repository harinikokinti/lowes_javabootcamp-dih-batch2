package com.lowes.bankingapp.transaction.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lowes.bankingapp.transaction.exception.TransactionNotFoundException;
import com.lowes.bankingapp.transaction.model.Transaction;
import com.lowes.bankingapp.transaction.repository.TransactionRepository;

@Service
public class TransactionService {
	Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	TransactionRepository repo;

	// get Transaction by id
	public Transaction get(int id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new TransactionNotFoundException("Sorry Transaction not found in the database", e);
		}
	}

	// update Transaction by id
	public Transaction update(int id, Transaction transactionNew) {
		try {
			Transaction transById = repo.findById(id).get();
			transactionNew.setTransactionTime(transById.getTransactionTime());
			return repo.save(transactionNew);
		} catch (NoSuchElementException e) {
			throw new TransactionNotFoundException("Sorry Transaction not found in the database", e);
		}
	}

	// get all Transactions
	public List<Transaction> getAll() {
		List<Transaction> transactionsList = repo.findAll();
		if (transactionsList.isEmpty()) {
			throw new TransactionNotFoundException("Sorry no Transaction found in the database");
		}
		return transactionsList;
	}

	// delete Transaction by id
	public void delete(int id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new TransactionNotFoundException("Sorry Transaction not found in the database", e);
		}
	}

	// Handler
	@KafkaListener(topics = "FUNDTRANSFER_CREATED", groupId = "transaction-service", clientIdPrefix = "trasaction-service")
	public void listenFundTransferCreated(ConsumerRecord<?, ?> cr) throws Exception {
		System.out.println("###################Fund Transfer Created: " + cr.value());

		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String sourceAccId = tokens[0];
		String targetAccId = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];

		int nsourceAccId = Integer.parseInt(sourceAccId);
		int ntargetAccId = Integer.parseInt(targetAccId);
		double damount = Double.valueOf(amount);

		// Create debit transaction
		Transaction debitTransaction = new Transaction();
		debitTransaction.setAccountId(nsourceAccId);
		debitTransaction.setAmount(damount);
		debitTransaction.setDescription(description);
		debitTransaction.setType("debit");
		repo.save(debitTransaction);

		// Create credit transaction
		Transaction creditTransaction = new Transaction();
		creditTransaction.setAccountId(ntargetAccId);
		creditTransaction.setAmount(damount);
		creditTransaction.setDescription(description);
		creditTransaction.setType("credit");
		repo.save(creditTransaction);

	}

	// Rollback Handler
	@KafkaListener(topics = "ROLLBACK_CREATED", groupId = "transaction-rollback-service", clientIdPrefix = "trasaction-roll-service")
	public void listenRollbackFromAccount(ConsumerRecord<?, ?> rollcr) throws Exception {
		System.out.println("################### Roll Back Created ################### : " + rollcr.value());

		String msg = (String) rollcr.value();
		String[] tokens = msg.split(",");
		String sourceAccId = tokens[0];
		String targetAccId = tokens[1];
		String amount = tokens[2];
		String description = tokens[3];

		int nsourceAccId = Integer.parseInt(sourceAccId);
		int ntargetAccId = Integer.parseInt(targetAccId);
		double damount = Double.valueOf(amount);

		// Create rollback debit transaction
		Transaction debitTransaction = new Transaction();
		debitTransaction.setAccountId(nsourceAccId);
		debitTransaction.setAmount(damount);
		debitTransaction.setDescription(description);
		debitTransaction.setType("rollback-credit");
		repo.save(debitTransaction);

		// Create rollback credit transaction
		Transaction creditTransaction = new Transaction();
		creditTransaction.setAccountId(ntargetAccId);
		creditTransaction.setAmount(damount);
		creditTransaction.setDescription(description);
		creditTransaction.setType("rollback-debit");
		repo.save(creditTransaction);

	}
}

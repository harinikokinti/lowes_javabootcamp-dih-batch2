package com.lowes.bankingapp.account.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.lowes.bankingapp.account.exception.AccountNotFoundException;
import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.repository.AccountRepository;

@Service
public class AccountService {

	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	AccountRepository repo;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	// create account
	public Account create(Account account) {
		return repo.save(account);
	}

	// get account by id
	public Account get(int id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException("Sorry Account not found in the database", e);
		}
	}

	// update account by id
	public Account update(int id, Account accNew) {
		try {
			Account accById = repo.findById(id).get();
			accNew.setOpenDate(accById.getOpenDate());
			return repo.save(accNew); // doubt, how to avoid full update
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException("Sorry Account not found in the database", e);
		}
	}

	// get all accounts
	public List<Account> getAll() {
		List<Account> accList = repo.findAll();
		if (accList.isEmpty()) {
			throw new AccountNotFoundException("Sorry no account found in the database");
		}
		return accList;
	}

	// delete account by id
	public void delete(int id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("Sorry Account not found in the database", e);
		}
	}

	// delete all

	public void clear() {
		repo.deleteAll();
	}

	// Handler

	@KafkaListener(topics = "FUNDTRANSFER_CREATED", groupId = "account-service")
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

		Account accByTgtId = repo.findById(ntargetAccId).get();

		if ("active".equals(accByTgtId.getStatus())) {
			// debit amount from source account balance
			Account accBySrcId = repo.findById(nsourceAccId).get();
			accBySrcId.setBalance(accBySrcId.getBalance() - damount);
			repo.save(accBySrcId);
			// credit amount from target account balance
			accByTgtId.setBalance(accByTgtId.getBalance() + damount);
			repo.save(accByTgtId);
		} else {
			kafkaTemplate.send("ROLLBACK_CREATED", msg);
		}

	}
}

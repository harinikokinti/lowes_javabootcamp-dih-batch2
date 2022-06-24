package com.lowes.bankingapp.transaction.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.transaction.model.ResponseMessage;
import com.lowes.bankingapp.transaction.model.Transaction;
import com.lowes.bankingapp.transaction.service.TransactionService;

@RestController
//@RequestMapping("/transactions")
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	// Get all transaction
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Transaction> getAll() { // date format coming as string with ,
		return transactionService.getAll();
	}

	// Get transaction
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Transaction get(@PathVariable int id) {
		return transactionService.get(id);
	}

	// Update transaction
	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> update(@PathVariable int id, @Valid @RequestBody Transaction transaction) {
		transaction.setId(id);
		Transaction transactionUpdated = transactionService.update(id, transaction);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(transactionUpdated.getId())
				.toUri();
		ResponseMessage response = new ResponseMessage("Success", "Transaction updated successfully");
		return ResponseEntity.created(location).body(response);
	}

	// Delete transaction
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> delete(@PathVariable int id) {
		transactionService.delete(id);
		ResponseMessage response = new ResponseMessage("Success", "Transaction deleted successfully");
		return ResponseEntity.ok().body(response);
	}
}

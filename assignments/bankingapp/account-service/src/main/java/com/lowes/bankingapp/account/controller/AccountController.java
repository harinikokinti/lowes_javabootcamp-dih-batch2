package com.lowes.bankingapp.account.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.model.ResponseMessage;
import com.lowes.bankingapp.account.service.AccountService;

@RestController
//@RequestMapping("/accounts")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AccountService accountService;

	// Create Account
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody Account account) throws URISyntaxException {
		Account accCreated = accountService.create(account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accCreated.getId())
				.toUri();
		ResponseMessage response = new ResponseMessage("Success", "Account created successfully");
		return ResponseEntity.created(location).body(response);
	}

	// Get all account
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Account> getAll() { // date format coming as string with ,
		return accountService.getAll();
	}

	// Get account
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Account get(@PathVariable int id) {
		return accountService.get(id);
	}

	// Update account
	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> update(@PathVariable int id, @Valid @RequestBody Account account) {
		account.setId(id);
		Account accUpdated = accountService.update(id, account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(accUpdated.getId()).toUri();
		ResponseMessage response = new ResponseMessage("Success", "Account updated successfully");
		return ResponseEntity.created(location).body(response);
	}

	// Delete account
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> delete(@PathVariable int id) {
		accountService.delete(id);
		ResponseMessage response = new ResponseMessage("Success", "Account deleted successfully");
		return ResponseEntity.ok().body(response);
	}

}

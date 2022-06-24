package com.lowes.bankingapp.transaction.model;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank(message = "type cannot be blank")
	private String type;

	@NotBlank(message = "description cannot be blank")
	private String description;

	private double amount;

	private int accountId;

	@Basic
	private LocalDateTime transactionTime;

	@PrePersist
	private void onCreate() {
		transactionTime = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

}

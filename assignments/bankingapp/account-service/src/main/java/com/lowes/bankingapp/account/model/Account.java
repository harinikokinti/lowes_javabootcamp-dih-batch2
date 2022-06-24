package com.lowes.bankingapp.account.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Account {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank(message = "name cannot be blank")
	private String name;

	@NotBlank(message = "type cannot be blank")
	private String type;

	private String status;

	@NotNull
	@Min(value = 1, message = "Minimum balance > 0")
	private double balance;

	@Basic
	private LocalDate openDate;

	@PrePersist
	private void onCreate() {
		openDate = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

}

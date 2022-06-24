package com.lowes.bankingapp.fundtransfer.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FundTransfer {

	@NotBlank(message = "sourceAccId cannot be blank")
	private String sourceAccId;

	@NotBlank(message = "targetAccId cannot be blank")
	private String targetAccId;

	private String description;

	@NotNull
	@Min(value = 1, message = "Minimum amount > 0")
	private double amount;

	public String getSourceAccId() {
		return sourceAccId;
	}

	public void setSourceAccId(String sourceAccId) {
		this.sourceAccId = sourceAccId;
	}

	public String getTargetAccId() {
		return targetAccId;
	}

	public void setTargetAccId(String targetAccId) {
		this.targetAccId = targetAccId;
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

}

package com.lowes.bankingapp.account.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // includes only not null fields in the json response
public class ResponseMessage {

	private String status;
	private String message;
	private List<String> errors;

	public ResponseMessage() {

	}

	public ResponseMessage(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseMessage(String status, List<String> errors) {
		this.status = status;
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}

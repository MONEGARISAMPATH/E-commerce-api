package com.e_commerce.OrderService.ErrorHandler;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private String errorMessage;
	private HttpStatus status;

	public ErrorMessage(String errorMessage, HttpStatus status) {
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}

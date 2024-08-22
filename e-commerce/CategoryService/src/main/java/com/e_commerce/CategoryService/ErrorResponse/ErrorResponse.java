package com.e_commerce.CategoryService.ErrorResponse;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private String errorMessage;
	private HttpStatus status;
	public ErrorResponse() {
		
	}
	public ErrorResponse(String errorMessage, HttpStatus status) {
		super();
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

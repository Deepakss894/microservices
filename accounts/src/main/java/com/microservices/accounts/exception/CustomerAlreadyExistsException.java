package com.microservices.accounts.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
	
	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}

}

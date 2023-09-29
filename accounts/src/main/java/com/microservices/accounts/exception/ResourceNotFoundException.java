package com.microservices.accounts.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue) {
		super(String.format("%s value is not with %s = %s",resourceName,fieldName,fieldValue));
	}
}

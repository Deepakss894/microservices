package com.microservices.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microservices.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exp,
			WebRequest webRequest) {

		ErrorResponseDto errorResponse = new ErrorResponseDto(webRequest.getDescription(false), exp.getMessage(),
				HttpStatus.NOT_FOUND, LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

	}

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExitsException(CustomerAlreadyExistsException exp,
			WebRequest webRequest) {

		ErrorResponseDto errorResponse = new ErrorResponseDto(webRequest.getDescription(false), exp.getMessage(),
				HttpStatus.BAD_REQUEST, LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

	}
}

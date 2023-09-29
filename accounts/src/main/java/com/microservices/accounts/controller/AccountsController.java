package com.microservices.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.SuccessResponseDto;
import com.microservices.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces={MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
	
	private IAccountsService iAccountsService;

	@PostMapping(path = "/createAccount")
	public ResponseEntity<SuccessResponseDto> createAccount(@RequestBody CustomerDto customerDto){
		
		iAccountsService.createAccount(customerDto);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
		
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchCustomer(@RequestParam String mobileNumber){
		CustomerDto customerDto = iAccountsService.fetchCustomerDetails(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		
	}

}

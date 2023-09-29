package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface IAccountsService {

	
	public void createAccount(CustomerDto customerDto);
	
	public CustomerDto fetchCustomerDetails(String mobileNumber);
}

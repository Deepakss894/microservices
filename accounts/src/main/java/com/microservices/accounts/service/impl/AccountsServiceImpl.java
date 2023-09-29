package com.microservices.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.CustomerAlreadyExistsException;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{
	
	AccountsRepository accountRepo;
	
	CustomerRepository customerRepo;
	
	public void createAccount(CustomerDto customerDto) {
		
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> customerAlreadyExistCheck = customerRepo.findByMobileNumber(customer.getMobileNumber());
		if(customerAlreadyExistCheck.isPresent()) {
			throw new CustomerAlreadyExistsException("customer already exists with mobile number "+customer.getMobileNumber());
		}
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("admin");
		customerRepo.save(customer);
		accountRepo.save(createNewAccount(customer));
		
	}
	
	private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(customer.getCreatedAt());
        newAccount.setCreatedBy(customer.getCreatedBy());
        return newAccount;
    }

	@Override
	public CustomerDto fetchCustomerDetails(String mobileNumber) {
		
		Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
		
		Accounts accounts = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Account", "mobileNumber", mobileNumber));
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		
		return customerDto;
	}

}

package com.microservices.accounts.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Optional<Customer> findByMobileNumber(String mobileNumber);

}

package com.microservices.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{

	public Optional<Accounts> findByCustomerId(Long customerId);
}

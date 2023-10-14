package com.coding.graychain_practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.graychain_practical.entity.Customer;
import com.coding.graychain_practical.entity.Lender;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	Lender findByName(String name);
}

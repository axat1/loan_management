package com.coding.graychain_practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.graychain_practical.entity.Lender;

@Repository
public interface LenderRepository extends JpaRepository<Lender, String> {

	Lender findByName(String name);
}

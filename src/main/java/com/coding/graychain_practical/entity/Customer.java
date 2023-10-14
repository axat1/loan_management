package com.coding.graychain_practical.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
	private String customerId;
	
    @Column(name="name")
	private String name;
    
//    @OneToMany(mappedBy="customer")
//    private List<Loan> loans;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Loan> getLoans() {
//		return loans;
//	}
//
//	public void setLoans(List<Loan> loans) {
//		this.loans = loans;
//	}
    
}

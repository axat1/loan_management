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
@Table(name="lender")
public class Lender {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lender_id")
	private String lenderId;
	
	@Column(name="name")
	private String name;
	
//    @OneToMany(mappedBy="lender")
//    private List<Loan> loans;

	public String getLenderId() {
		return lenderId;
	}

	public void setLenderId_id(String lenderId) {
		this.lenderId = lenderId;
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

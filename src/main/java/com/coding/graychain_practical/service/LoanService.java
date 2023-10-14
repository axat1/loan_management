package com.coding.graychain_practical.service;

import java.util.List;

import com.coding.graychain_practical.entity.Loan;
import com.coding.graychain_practical.entity.LoanAggregate;

public interface LoanService {

	public List<Loan> getLoans();
	
	public Loan addLoan(Loan loan);
	
	public List<Loan> getLoanByCustomerId(String customerId);
	
	public List<Loan> getLoanByLenderId(String lenderId);
	
	public Loan getLoanByLoanId(String loanId);
	
	List<LoanAggregate> getAggregateLoansByInterest();

	List<LoanAggregate> getAggregateLoansByCustomer();

	List<LoanAggregate> getAggregateLoansByLender();
}

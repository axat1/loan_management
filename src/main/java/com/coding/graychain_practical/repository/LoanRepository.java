package com.coding.graychain_practical.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coding.graychain_practical.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

	Loan findByLoanId(String loanId);
	List<Loan> findByCustomerCustomerId(String customerId);
    List<Loan> findByLenderLenderId(String lenderId);
    
    @Query("SELECT \r\n" + 
    		"	l.lender.lenderId AS id,\r\n" + 
    		"	SUM(l.remaining_amount) AS totalRemainingAmount,\r\n" + 
    		"	SUM(l.interestPerDay) AS totalInterest,\r\n" + 
    		"	SUM(l.penaltyPerDay) AS totalPenalty\r\n" + 
    		"FROM \r\n" + 
    		"	Loan l \r\n" + 
    		"GROUP BY \r\n" + 
    		"	l.lender.lenderId")
     List<Tuple> getAggregateLoansByLender();

     @Query("SELECT \r\n" + 
     		"    l.customer.customerId AS id,\r\n" + 
     		"    SUM(l.remaining_amount) AS totalRemainingAmount,\r\n" + 
     		"    SUM(l.interestPerDay) AS totalInterest,\r\n" + 
     		"    SUM(l.penaltyPerDay) AS totalPenalty \r\n" + 
     		"FROM \r\n" + 
     		"	Loan l \r\n" + 
     		"GROUP BY \r\n" + 
     		"	l.customer.customerId")
     List<Tuple> getAggregateLoansByCustomer();

     @Query("SELECT \r\n" + 
     		"    l.interestPerDay AS id,\r\n" + 
     		"    SUM(l.remaining_amount) AS totalRemainingAmount,\r\n" + 
     		"    SUM(l.interestPerDay) AS totalInterest,\r\n" + 
     		"    SUM(l.penaltyPerDay) AS totalPenalty\r\n" + 
     		"FROM \r\n" + 
     		"	Loan l\r\n" + 
     		"GROUP BY \r\n" + 
     		"	l.interestPerDay")
     List<Tuple> getAggregateLoansByInterest();
}

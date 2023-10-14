package com.coding.graychain_practical.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.graychain_practical.entity.Loan;
import com.coding.graychain_practical.entity.LoanAggregate;
import com.coding.graychain_practical.exception.LoanNotFoundException;
import com.coding.graychain_practical.exception.LoanValidationException;
import com.coding.graychain_practical.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{

	private final LoanRepository loanRepository;
	
	@Autowired
	public LoanServiceImpl(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	@Override
	public List<Loan> getLoans() {
		return loanRepository.findAll();
	}

	@Override
	public Loan addLoan(Loan loan) {
		validateLoan(loan);
		Loan l = loanRepository.save(loan);
		return l;
	}

	private void validateLoan(Loan loan) {
		if(loan.getPaymentDate().after(loan.getDueDate())){
			throw new LoanValidationException("Payment date cannot be greater than the due date.");
		}
	}

	@Override
	public List<Loan> getLoanByCustomerId(String customerId) {
		List<Loan> loans = loanRepository.findByCustomerCustomerId(customerId);
		if(loans.isEmpty()) {
			throw new LoanNotFoundException("No loans found with the specified criteria.");
		}
		return loans;
	}

	@Override
	public List<Loan> getLoanByLenderId(String lenderId) {
		List<Loan> loans = loanRepository.findByLenderLenderId(lenderId);
		if(loans.isEmpty()) {
			throw new LoanNotFoundException("No loans found with the specified criteria.");
		}
		return loans;
	}

	@Override
	public Loan getLoanByLoanId(String loanId) {
		return loanRepository.findById(loanId)
					.orElseThrow(() -> new LoanNotFoundException("No loans found with the specified criteria."));
	}

	@Override
	public List<LoanAggregate> getAggregateLoansByLender() {
		List<Tuple> tupleResults = loanRepository.getAggregateLoansByLender();
		List<LoanAggregate> loanAggregates = tupleResults.stream()
			    .map(tuple -> new LoanAggregate(
			        tuple.get("id", String.class),
			        tuple.get("totalRemainingAmount", BigDecimal.class),
			        tuple.get("totalInterest", Double.class),
			        tuple.get("totalPenalty", Double.class)
			    ))
			    .collect(Collectors.toList());

        return loanAggregates;
    }

	@Override
    public List<LoanAggregate> getAggregateLoansByCustomer() {
		List<Tuple> tupleResults = loanRepository.getAggregateLoansByCustomer();
		List<LoanAggregate> loanAggregates = tupleResults.stream()
			    .map(tuple -> new LoanAggregate(
			        tuple.get("id", String.class),
			        tuple.get("totalRemainingAmount", BigDecimal.class),
			        tuple.get("totalInterest", Double.class),
			        tuple.get("totalPenalty", Double.class)
			    ))
			    .collect(Collectors.toList());

        return loanAggregates;
    }

	@Override
    public List<LoanAggregate> getAggregateLoansByInterest() {
		List<Tuple> tupleResults = loanRepository.getAggregateLoansByInterest();
		List<LoanAggregate> loanAggregates = tupleResults.stream()
			    .map(tuple -> new LoanAggregate(
			        tuple.get("id", String.class),
			        tuple.get("totalRemainingAmount", BigDecimal.class),
			        tuple.get("totalInterest", Double.class),
			        tuple.get("totalPenalty", Double.class)
			    ))
			    .collect(Collectors.toList());

        return loanAggregates;
    }


}

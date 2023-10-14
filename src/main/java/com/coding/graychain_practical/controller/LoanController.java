package com.coding.graychain_practical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.graychain_practical.entity.Loan;
import com.coding.graychain_practical.entity.LoanAggregate;
import com.coding.graychain_practical.exception.LoanNotFoundException;
import com.coding.graychain_practical.exception.LoanValidationException;
import com.coding.graychain_practical.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/all")
    public List<Loan> getAllLoans() {
        return loanService.getLoans();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLoan(@RequestBody Loan loan) {
        try {
            loanService.addLoan(loan);
            return ResponseEntity.ok("Loan added successfully");
        } catch (LoanValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String loanId) {
        Loan loan = loanService.getLoanByLoanId(loanId);
        if (loan != null) {
            return ResponseEntity.ok(loan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<Loan> getLoansByCustomerId(@PathVariable String customerId) {
        return loanService.getLoanByCustomerId(customerId);
    }

    @GetMapping("/lender/{lenderId}")
    public List<Loan> getLoansByLenderId(@PathVariable String lenderId) {
        return loanService.getLoanByLenderId(lenderId);
    }

    @GetMapping("/aggregate/lender")
    public ResponseEntity<List<LoanAggregate>> getAggregateLoansByLender() {
        try {
            List<LoanAggregate> aggregates = loanService.getAggregateLoansByLender();
            return new ResponseEntity<>(aggregates, HttpStatus.OK);
        } catch (Exception e) {
            throw new LoanNotFoundException("No loans found by lender");
        }
    }

    @GetMapping("/aggregate/customer")
    public ResponseEntity<List<LoanAggregate>> getAggregateLoansByCustomer() {
        try {
            List<LoanAggregate> aggregates = loanService.getAggregateLoansByCustomer();
            return new ResponseEntity<>(aggregates, HttpStatus.OK);
        } catch (Exception e) {
            throw new LoanNotFoundException("No loans found by customer");
        }
    }

    @GetMapping("/aggregate/interest")
    public ResponseEntity<List<LoanAggregate>> getAggregateLoansByInterest() {
        try {
            List<LoanAggregate> aggregates = loanService.getAggregateLoansByInterest();
            return new ResponseEntity<>(aggregates, HttpStatus.OK);
        } catch (Exception e) {
            throw new LoanNotFoundException("No loans found by interest");
        }
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<String> handleLoanNotFoundException(LoanNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

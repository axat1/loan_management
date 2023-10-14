package com.coding.graychain_practical.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

@Entity
@Table(name="loan")
public class Loan {

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_id")
	private String loanId = UUID.randomUUID().toString();

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="lender_id")
	private Lender lender;
	
	@DecimalMin(value = "0.01", message = "Amount must be greater than 0.01")
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format")
	@Column(name="amount")
	private BigDecimal amount;
	
	@DecimalMin(value = "0.00", message = "Remaining amount must be greater than or equal to 0.00")
    @Digits(integer = 10, fraction = 2, message = "Invalid remaining amount format")
	@Column(name="remaining_amount")
	private BigDecimal remaining_amount;

	@FutureOrPresent(message = "Payment date must be in the future or today")
	@Column(name="payment_date")
	private Date paymentDate;
	
	@Future(message = "Due date must be in the future")
	@Column(name="due_date")
	private Date dueDate;
	
	@DecimalMin(value = "0.00", message = "Interest per day must be greater than or equal to 0.00")
    @Digits(integer = 5, fraction = 2, message = "Invalid interest format")
	@Column(name="interest_perday")
	private Double interestPerDay;
	
	@DecimalMin(value = "0.00", message = "Penalty per day must be greater than or equal to 0.00")
    @Digits(integer = 5, fraction = 2, message = "Invalid penalty format")
	@Column(name="penalty_perday")
	private Double penaltyPerDay;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Lender getLender() {
		return lender;
	}

	public void setLender(Lender lender) {
		this.lender = lender;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRemaining_amount() {
		return remaining_amount;
	}

	public void setRemaining_amount(BigDecimal remaining_amount) {
		this.remaining_amount = remaining_amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getInterestPerDay() {
		return interestPerDay;
	}

	public void setInterestPerDay(Double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}

	public Double getPenaltyPerDay() {
		return penaltyPerDay;
	}

	public void setPenaltyPerDay(Double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}
	
}

package com.coding.graychain_practical.entity;

import java.math.BigDecimal;

public class LoanAggregate {
	private String id;
    private BigDecimal totalRemainingAmount;
    private Double totalInterest;
    private Double totalPenalty;

    public LoanAggregate() {};
    
    public LoanAggregate(String id, BigDecimal totalRemainingAmount, Double totalInterest, Double totalPenalty) {
    	this.id = id;
    	this.totalRemainingAmount = totalRemainingAmount;
        this.totalInterest = totalInterest;
        this.totalPenalty = totalPenalty;
    }

	public void setId(String id) {
		this.id = id;
	}

    public BigDecimal getTotalRemainingAmount() {
        return totalRemainingAmount;
    }

    public void setTotalRemainingAmount(BigDecimal totalRemainingAmount) {
        this.totalRemainingAmount = totalRemainingAmount;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Double getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(Double totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

	public String getId() {
		return id;
	}
    
}


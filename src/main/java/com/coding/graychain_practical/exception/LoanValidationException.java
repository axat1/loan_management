package com.coding.graychain_practical.exception;

public class LoanValidationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanValidationException(String message) {
        super(message);
    }
}

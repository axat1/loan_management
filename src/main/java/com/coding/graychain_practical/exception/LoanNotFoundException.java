package com.coding.graychain_practical.exception;

public class LoanNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanNotFoundException(String message) {
        super(message);
    }
}

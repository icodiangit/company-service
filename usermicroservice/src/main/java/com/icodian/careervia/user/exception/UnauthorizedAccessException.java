package com.icodian.careervia.user.exception;

public class UnauthorizedAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedAccessException(String message) {
		super(message);
	}
	
	public UnauthorizedAccessException() {
		super("You have no access to this page.");
	}
	
	

}

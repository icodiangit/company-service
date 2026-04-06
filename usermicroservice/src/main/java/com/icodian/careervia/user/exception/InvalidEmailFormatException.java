package com.icodian.careervia.user.exception;

public class InvalidEmailFormatException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidEmailFormatException (String message) {
		super(message);
	}
	
	public InvalidEmailFormatException() {
		super("Please check your email.");
	}

}

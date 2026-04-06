package com.icodian.careervia.user.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException() {
		super("Check Email and Password and TRY AGAIN!!!");
	}


}

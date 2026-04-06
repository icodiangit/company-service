package com.icodian.careervia.user.exception;

public class InvalidUserDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUserDataException (String message) {
		super(message);
	}
	
	public InvalidUserDataException() {
		super("Invlaid! Please fill all the fields with '*' symbol.");
	}

}

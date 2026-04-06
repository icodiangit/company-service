package com.icodian.careervia.user.exception;

public class DuplicateskillException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateskillException (String message) {
		super(message);
	}
	
	public DuplicateskillException() {
		super("This skill arleady exists. Try adding another one.");
	}

}

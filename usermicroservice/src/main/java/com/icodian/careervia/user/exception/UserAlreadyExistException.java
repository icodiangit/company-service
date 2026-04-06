package com.icodian.careervia.user.exception;

public class UserAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String message) {
		super(message);
	}
	
	public UserAlreadyExistException() {
		super("User with these credentials already exisits. Try Loging-In");
	}
}

package com.icodian.careervia.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException() {
		super("User Not Fetched on Server");
	}

}

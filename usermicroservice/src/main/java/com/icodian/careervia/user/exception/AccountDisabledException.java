package com.icodian.careervia.user.exception;

public class AccountDisabledException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AccountDisabledException (String message) {
		super(message);
	}
	
	public AccountDisabledException () {
		super("Sorry but your account is disabled");
	}

}

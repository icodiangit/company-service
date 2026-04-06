package com.icodian.careervia.job.exceptions;

public class CompanyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 111093339552983662L;

	public CompanyNotFoundException(String message) {
		super(message);
	}

	public CompanyNotFoundException() {
		super("Company Not Found.");
	}

	
	
}

package com.icodian.careervia.job.exceptions;

public class ApplicationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -374877948843696219L;

	public ApplicationNotFoundException() {
		super("Application not found on server.");
		// TODO Auto-generated constructor stub
	}

	public ApplicationNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

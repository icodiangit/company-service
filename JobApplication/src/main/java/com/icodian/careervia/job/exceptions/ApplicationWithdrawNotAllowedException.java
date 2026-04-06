package com.icodian.careervia.job.exceptions;

public class ApplicationWithdrawNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1819129081440193458L;

	public ApplicationWithdrawNotAllowedException() {
		super("Application withdrawal not allowed after selection.");
	}

	public ApplicationWithdrawNotAllowedException(String message) {
		super(message);
	}

}

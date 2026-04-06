package com.icodian.careervia.job.exceptions;

public class JobExpiredException extends RuntimeException {

	private static final long serialVersionUID = 134993002141275652L;

	public JobExpiredException() {
		super("Job last date has passed out.");
	}

	public JobExpiredException(String message) {
		super(message);
	}

}

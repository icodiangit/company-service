package com.icodian.careervia.job.exceptions;

public class JobNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7236916817161157104L;

	public JobNotFoundException(String message) {
		super(message);
	}

	public JobNotFoundException() {
		super("Job not found on server.");
	}

}

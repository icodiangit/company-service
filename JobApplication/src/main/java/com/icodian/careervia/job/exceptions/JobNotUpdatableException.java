package com.icodian.careervia.job.exceptions;

public class JobNotUpdatableException extends RuntimeException{

	private static final long serialVersionUID = 2186485191845614309L;

	public JobNotUpdatableException(String message) {
		super(message);
	}

	public JobNotUpdatableException() {
		super("Job not updateable.");
	}
	
	

}

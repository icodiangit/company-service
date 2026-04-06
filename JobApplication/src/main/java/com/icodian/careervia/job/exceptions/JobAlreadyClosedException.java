package com.icodian.careervia.job.exceptions;

public class JobAlreadyClosedException extends RuntimeException {

	private static final long serialVersionUID = 2359151590776288936L;

	public JobAlreadyClosedException() {
		super("Job already closed.");
	}

	public JobAlreadyClosedException(String message) {
		super(message);
	}

}

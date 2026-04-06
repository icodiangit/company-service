package com.icodian.careervia.job.exceptions;

public class UnauthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = -1292698300636348409L;

	public UnauthorizedAccessException() {
		super("Job seekers can only view active jobs");
	}

	public UnauthorizedAccessException(String message) {
		super(message);
	}

}

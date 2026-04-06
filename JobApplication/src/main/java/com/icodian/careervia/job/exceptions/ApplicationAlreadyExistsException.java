package com.icodian.careervia.job.exceptions;

public class ApplicationAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -69568578768626227L;

	public ApplicationAlreadyExistsException() {
		super("Application applied again for the same job.");
	}

	public ApplicationAlreadyExistsException(String message) {
		super(message);
	}

}

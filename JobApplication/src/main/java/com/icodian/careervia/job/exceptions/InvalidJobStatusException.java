package com.icodian.careervia.job.exceptions;

public class InvalidJobStatusException extends RuntimeException {

	private static final long serialVersionUID = -8029838437051053786L;

	public InvalidJobStatusException() {
		super("Invalid status transition.");
	}

	public InvalidJobStatusException(String message) {
		super(message);
	}

}

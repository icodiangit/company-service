package com.icodian.careervia.job.exceptions;

public class InvalidApplicationStatusException extends RuntimeException {

	private static final long serialVersionUID = 3566544100913147152L;

	public InvalidApplicationStatusException() {
		super("Wrong Status update.");
	}

	public InvalidApplicationStatusException(String message) {
		super(message);
	}

}

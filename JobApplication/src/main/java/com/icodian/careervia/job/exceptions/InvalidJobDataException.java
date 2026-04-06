package com.icodian.careervia.job.exceptions;

public class InvalidJobDataException extends RuntimeException {

	private static final long serialVersionUID = 7402607716453783321L;

	public InvalidJobDataException() {
		super("Invalid data for the job.");
	}

	public InvalidJobDataException(String message) {
		super(message);
	}


}

package com.icodian.careervia.job.exceptions;

public class ServiceCommunicationException extends RuntimeException{

	private static final long serialVersionUID = -8130529196034712571L;

	public ServiceCommunicationException(String message) {
		super(message);
	}

	public ServiceCommunicationException() {
		super("Service Communication is not available.");
	}
	
	

}

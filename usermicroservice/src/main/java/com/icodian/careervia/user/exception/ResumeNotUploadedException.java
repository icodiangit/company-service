package com.icodian.careervia.user.exception;

public class ResumeNotUploadedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResumeNotUploadedException(String message) {
		super(message);
	}
	
	public ResumeNotUploadedException() {
		super("Wait...kindly upload your resume before proceding");
	}
	
	

}

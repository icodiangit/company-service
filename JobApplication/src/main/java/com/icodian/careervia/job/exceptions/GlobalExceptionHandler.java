package com.icodian.careervia.job.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icodian.careervia.job.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(JobNotFoundException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApplicationNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ApplicationNotFoundException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApplicationAlreadyExistsException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ApplicationAlreadyExistsException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.CONFLICT)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ApplicationWithdrawNotAllowedException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ApplicationWithdrawNotAllowedException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidApplicationStatusException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(InvalidApplicationStatusException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJobStatusException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(InvalidJobStatusException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JobAlreadyClosedException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(JobAlreadyClosedException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JobExpiredException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(JobExpiredException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJobDataException.class)
	public ResponseEntity<ApiResponse> handleInvalidJobDataException(InvalidJobDataException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.BAD_REQUEST)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.FORBIDDEN)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ApiResponse> handleCompanyNotFoundException(CompanyNotFoundException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceCommunicationException.class)
	public ResponseEntity<ApiResponse> handleServiceCommunicationException(ServiceCommunicationException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse apiResponse = ApiResponse.builder()
				.message(message)
				.success(false)
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.build();
		
		return new ResponseEntity<>(apiResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
}

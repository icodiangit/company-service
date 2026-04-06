package com.icodian.careervia.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icodian.careervia.user.payload.APIResponse;

//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountDisabledException.class)
	public ResponseEntity<APIResponse> handleResourceDisabledException(AccountDisabledException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateskillException.class)
	public ResponseEntity<APIResponse> handleDuplicateResourseException(DuplicateskillException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<APIResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidEmailFormatException.class)
	public ResponseEntity<APIResponse> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidUserDataException.class)
	public ResponseEntity<APIResponse> handleInvalidUserDataException(InvalidUserDataException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ResumeNotUploadedException.class)
	public ResponseEntity<APIResponse> handleResumeNotUploadedException(ResumeNotUploadedException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<APIResponse> handleUserAlredayExistException(UnauthorizedAccessException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<APIResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false)
				.status(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<APIResponse> handleUserNotFoundException(UserNotFoundException ex) {

		String message = ex.getMessage();

		APIResponse apiResponse = APIResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

}

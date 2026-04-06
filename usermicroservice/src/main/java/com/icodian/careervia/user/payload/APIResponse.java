package com.icodian.careervia.user.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse {

	private String message;
	private boolean success;
	private HttpStatus status;
}

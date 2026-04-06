package com.icodian.careervia.user.dto;

import lombok.Data;

@Data
public class UserCreateRequestDTO {
	
	private String email;
	
	private String phone;
	
	private String role;
	
	private String status;
	
	private String password;

}

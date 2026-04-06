package com.icodian.careervia.user.dto;

import com.icodian.careervia.user.enums.Role;

import lombok.Data;

@Data
public class LoginResponseDTO {
	
	private Long userId;
	private String email;
	private Role role;
	private String token;
	private String status;

}

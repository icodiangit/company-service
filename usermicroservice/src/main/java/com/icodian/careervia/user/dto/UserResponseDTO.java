package com.icodian.careervia.user.dto;

import java.util.Date;
import java.util.List;

import com.icodian.careervia.user.enums.Role;
import com.icodian.careervia.user.enums.UserStatus;

import lombok.Data;

@Data
public class UserResponseDTO {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Role role;
	private UserStatus status;
	private Date createdAt;
	
	private UserProfileDTO profile;
	private List<SkillDTO> skills;
	private List<CourseDTO> courses;
	private List<ResumeDTO> resume;

}

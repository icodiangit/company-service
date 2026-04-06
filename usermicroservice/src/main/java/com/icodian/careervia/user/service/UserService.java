package com.icodian.careervia.user.service;

import java.util.List;

import com.icodian.careervia.user.dto.ApplicationDTO;
import com.icodian.careervia.user.dto.CourseDTO;
import com.icodian.careervia.user.dto.LoginRequestDTO;
import com.icodian.careervia.user.dto.LoginResponseDTO;
import com.icodian.careervia.user.dto.ResumeDTO;
import com.icodian.careervia.user.dto.ResumeRequestDTO;
import com.icodian.careervia.user.dto.SkillDTO;
import com.icodian.careervia.user.dto.UserCourseRequestDTO;
import com.icodian.careervia.user.dto.UserCreateRequestDTO;
import com.icodian.careervia.user.dto.UserProfileDTO;
import com.icodian.careervia.user.dto.UserProfileRequestDTO;
import com.icodian.careervia.user.dto.UserResponseDTO;
import com.icodian.careervia.user.dto.UserSkillRequestDTO;
import com.icodian.careervia.user.dto.UserUpdateRequestDTO;

public interface UserService {
	//Login 
	LoginResponseDTO login(LoginRequestDTO request);
	
	//Creating, Updating, fetching user by their ID and fetching user by their email.
	
	UserResponseDTO createUser(UserCreateRequestDTO request);
	UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO request);
	UserResponseDTO getUserById(Long userId);
	UserResponseDTO getUserByEmail(String email);
	void deleteUser(Long userId);
	
	//Profile
	UserProfileDTO createOrUpdateProfile(Long userId, UserProfileRequestDTO request);
	UserProfileDTO getUserProfile(Long userId);
	
	//Skills
	void addSkillToUser(Long userId, UserSkillRequestDTO request);
	List<SkillDTO> getUserSkills(Long userId);
	void removeSkillFromUser(Long userId, Long skillId);
	
	//Courses
	void addCourse(Long userId, UserCourseRequestDTO request);
	List<CourseDTO> getCourses(Long userId);
	void removeCourse(Long userId, Long courseId);
	
	//Resume
	ResumeDTO addResume(Long userId, ResumeRequestDTO request);
	ResumeDTO updateResume(Long userId, Long resumeId, ResumeRequestDTO request);
	void deleteResume(Long userId, Long resumeId);
	
	//Application
	List<ApplicationDTO> getUserApplications(Long userId);
	
	
}

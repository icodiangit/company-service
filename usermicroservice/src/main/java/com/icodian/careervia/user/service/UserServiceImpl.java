package com.icodian.careervia.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.icodian.careervia.user.entity.User;
import com.icodian.careervia.user.entity.UserProfile;
import com.icodian.careervia.user.enums.Role;
import com.icodian.careervia.user.enums.UserStatus;
import com.icodian.careervia.user.exception.InvalidCredentialsException;
import com.icodian.careervia.user.exception.UserAlreadyExistException;
import com.icodian.careervia.user.exception.UserNotFoundException;
import com.icodian.careervia.user.repository.UserProfileRepository;
import com.icodian.careervia.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	// private final PasswordEncoder passwordEncoder;
	private final RestTemplate restTemplate;
	private final UserProfileRepository userProfileRepository;

	// CREATE USER
	@Override
	public UserResponseDTO createUser(UserCreateRequestDTO request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new UserAlreadyExistException("Email already registered");
		}

		User user = new User();
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setRole(Role.JOB_SEEKER);
		user.setStatus(UserStatus.ACTIVE);
		user.setCreatedAt(new Date());

		user.setPassword(request.getPassword());

		User savedUser = userRepository.save(user);

		// CREATE USER PROFILE
		UserProfile profile = new UserProfile();
		profile.setUser(savedUser);
		profile.setUpdatedAt(new Date());

		UserProfile savedProfile = userProfileRepository.save(profile);

		return mapToUserResponseDTO(savedUser);

	}

	// LOGIN
	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UserNotFoundException("Invalid Email"));

		if (!request.getPassword().equals(user.getPassword())) {
			throw new InvalidCredentialsException("Invalid Password");
		}

		LoginResponseDTO response = new LoginResponseDTO();
		response.setUserId(user.getUserId());
		response.setEmail(user.getEmail());
		response.setRole(Role.JOB_SEEKER);
		response.setToken("JWT TOKEN PLACEHOLDER");

		return response;
	}

	// GET USER BY ID
	@Override
	public UserResponseDTO getUserById(Long userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		return mapToUserResponseDTO(user);
	}

	// GET USER BY EMAIL
	@Override
	public UserResponseDTO getUserByEmail(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));

		return mapToUserResponseDTO(user);
	}

	// UPDATE USER
	@Override
	public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO request) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

		user.setPhone(request.getPhone());

		User updatedUser = userRepository.save(user);

		return mapToUserResponseDTO(updatedUser);
	}

	// DELETE USER
	@Override
	public void deleteUser(Long userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		userRepository.delete(user);
	}

	private UserResponseDTO mapToUserResponseDTO(User user) {

		UserResponseDTO dto = new UserResponseDTO();
		dto.setUserId(user.getUserId());
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setRole(user.getRole());
		dto.setStatus(user.getStatus());
		dto.setCreatedAt(user.getCreatedAt());

		// FETCH USER PROFILE
		UserProfile profile = userProfileRepository.findByUserUserId(user.getUserId()).orElse(null);

		if (profile != null) {
			UserProfileDTO profileDTO = new UserProfileDTO();
			profileDTO.setProfileId(profile.getProfileId());
			profileDTO.setBio(profile.getBio());
			profileDTO.setEducation(profile.getEducation());
			profileDTO.setExperience(profile.getExperience());
			profileDTO.setProfileStrength(profile.getProfileStrength());
			profileDTO.setUpdatedAt(profile.getUpdatedAt());

			dto.setProfile(profileDTO);
		}

		return dto;
	}

	@Override
	public UserProfileDTO createOrUpdateProfile(Long userId, UserProfileRequestDTO request) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		UserProfile profile = userProfileRepository.findByUserUserId(userId).orElseGet(() -> {
			UserProfile newProfile = new UserProfile();
			newProfile.setUser(user);
			return newProfile;
		});

		profile.setUser(user);
		profile.setFirstName(request.getFirstName());
		profile.setLastName(request.getLastName());
		profile.setLocation(request.getLocation());
		profile.setEducation(request.getEducation());
		profile.setExperience(request.getExperience());
		profile.setBio(request.getBio());
		profile.setUpdatedAt(new Date());

		// Calculating the Profile Strength
		int strength = 0;

		if (request.getBio() != null && !request.getBio().isEmpty()) {
			strength += 33;
		}

		if (request.getEducation() != null && !request.getEducation().isEmpty()) {
			strength += 33;
		}

		if (request.getExperience() != null && !request.getExperience().isEmpty()) {
			strength += 34;
		}

		profile.setProfileStrength(strength);

		UserProfile savedProfile = userProfileRepository.save(profile);

		UserProfileDTO dto = new UserProfileDTO();
		dto.setProfileId(savedProfile.getProfileId());
		dto.setFirstName(savedProfile.getFirstName());
		dto.setLastName(savedProfile.getLastName());
		dto.setEducation(savedProfile.getEducation());
		dto.setExperience(savedProfile.getExperience());
		dto.setBio(savedProfile.getBio());
		dto.setProfileStrength(savedProfile.getProfileStrength());
		dto.setUpdatedAt(savedProfile.getUpdatedAt());
		return dto;
	}

	@Override
	public UserProfileDTO getUserProfile(Long userId) {

		UserProfile profile = userProfileRepository.findByUserUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User profile not found "));

		UserProfileDTO dto = new UserProfileDTO();

		dto.setProfileId(profile.getProfileId());
		dto.setFirstName(profile.getFirstName());
		dto.setLastName(profile.getLastName());
		dto.setEducation(profile.getEducation());
		dto.setExperience(profile.getExperience());
		dto.setBio(profile.getBio());
		dto.setProfileStrength(profile.getProfileStrength());
		dto.setUpdatedAt(profile.getUpdatedAt());

		return dto;
	}

	@Override
	public void addSkillToUser(Long userId, UserSkillRequestDTO request) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SkillDTO> getUserSkills(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSkillFromUser(Long userId, Long skillId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourse(Long userId, UserCourseRequestDTO request) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CourseDTO> getCourses(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCourse(Long userId, Long courseId) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResumeDTO addResume(Long userId, ResumeRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResumeDTO updateResume(Long userId, Long resumeId, ResumeRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteResume(Long userId, Long resumeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ApplicationDTO> getUserApplications(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

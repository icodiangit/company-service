package com.icodian.careervia.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.user.dto.LoginRequestDTO;
import com.icodian.careervia.user.dto.LoginResponseDTO;
import com.icodian.careervia.user.dto.UserCreateRequestDTO;
import com.icodian.careervia.user.dto.UserProfileDTO;
import com.icodian.careervia.user.dto.UserProfileRequestDTO;
import com.icodian.careervia.user.dto.UserResponseDTO;
import com.icodian.careervia.user.dto.UserUpdateRequestDTO;
import com.icodian.careervia.user.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	// THIS IS USED FOR CREATING A NEW USER
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateRequestDTO request) {
		UserResponseDTO response = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// THIS IS USED TO LOGIN THE USER
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = userService.login(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// GET A USER BY USER's ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
		UserResponseDTO response = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// GET A USER BY USER's EMAIL
	@GetMapping("/email")
	public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam @Email String email) {
		UserResponseDTO response = userService.getUserByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// UPDATE THE USER INFO VIA THEIR ID
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
			@Valid @RequestBody UserUpdateRequestDTO request) {
		UserResponseDTO response = userService.updateUser(userId, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// DELETE THE EXISTING USER
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);

		log.info("User deleted successfully with ID: {}", userId);
		return ResponseEntity.noContent().build();
	}

	// GET THE USER PROFILE
	@GetMapping("/{userId}/profile")
	public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
		UserProfileDTO response = userService.getUserProfile(userId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// CREATE OR UPDATE USER PROFILE
	@PutMapping("/{userId}/profile")
	public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long userId,
			@Valid @RequestBody UserProfileRequestDTO request) {

		UserProfileDTO response = userService.createOrUpdateProfile(userId, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

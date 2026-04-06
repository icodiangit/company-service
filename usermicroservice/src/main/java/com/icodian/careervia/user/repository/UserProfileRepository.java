package com.icodian.careervia.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	Optional<UserProfile> findByUserUserId(Long userId);

}

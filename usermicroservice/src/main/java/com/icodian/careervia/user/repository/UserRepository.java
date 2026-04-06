package com.icodian.careervia.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email); // fetching user via their email address.

}

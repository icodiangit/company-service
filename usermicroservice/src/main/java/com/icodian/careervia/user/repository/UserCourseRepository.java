package com.icodian.careervia.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.user.entity.UserCourse;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
	
	List<UserCourse> findByUserUserId(Long userId); //fetching list of courses taken by the user via their id

}

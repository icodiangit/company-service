package com.icodian.careervia.user.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UserCourse")
public class UserCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; 
	//since, many users can have one single Course
	
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	//since, many UserCourse can have same course
	
	@Column(name = "progress")
	private Integer progress;
	
	@Column(name = "completion_status")
	private String completionStatus;
	
	@Column(name = "completed_at")
	private Date completedAt;
}

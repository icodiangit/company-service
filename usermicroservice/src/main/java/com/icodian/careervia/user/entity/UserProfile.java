package com.icodian.careervia.user.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id", updatable = false, nullable = false)
	private Long profileId;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "education")
	private String education;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "profile_strength")
	private Integer profileStrength;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}

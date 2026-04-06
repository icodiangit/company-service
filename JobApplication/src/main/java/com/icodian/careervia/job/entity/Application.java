package com.icodian.careervia.job.entity;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "application")
public class Application {

	@Id
	@Column(name = "appication_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicationId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "job_id")
	private Long jobId;
	
	@Column(name = "job_title")
	private String jobTitle;

//	@Column(name = "resume_id", length = 200)
//	private Long resume_id;

	@Column(name = "applied_date")
	private LocalDate appliedDate;
	
	@Column(name = "createdAt")
	private LocalDate createdAt;
	
	@Column(name = "message")
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(name = "application_status")
	private ApplicationStatus applicationStatus = ApplicationStatus.APPLIED;

	@Column(name = "remarks", columnDefinition = "TEXT")
	private String remarks;

	@Transient
	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;

}

package com.icodian.careervia.job.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "job")
public class Job {

	@Id
	@Column(name = "job_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "location", length = 150)
	private String location;

	@Column(name = "experience")
	private Integer experience;	

	@Column(name = "salary", length = 50)
	private Double salary;	

	@Enumerated(EnumType.STRING)
	@Column(name = "job_type")
	private JobType jobType = JobType.FULLTIME;

	@ElementCollection
	@Column(name = "skill")
	List<String> requiredSkills = new ArrayList<>();

	@Column(name = "posted_date")
	private LocalDate postedDate;

	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private JobStatus jobStatus = JobStatus.OPENED;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Application> application;
	
	@Transient
	private List<Company> company = new ArrayList<>();
	
	
}

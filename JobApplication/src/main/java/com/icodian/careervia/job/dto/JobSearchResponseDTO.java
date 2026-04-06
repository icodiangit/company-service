package com.icodian.careervia.job.dto;

import java.time.LocalDate;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import lombok.Data;

@Data
public class JobSearchResponseDTO {
	
	private Long jobId;
	private String jobTitle;
	private LocalDate postedDate;
	private JobStatus jobStatus;
	private List<String> requiredSkills;
	private Integer experience;
	private String location;
	private Double salary;
	private JobType jobType;
	private Long companyId;
	
}

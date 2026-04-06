package com.icodian.careervia.job.dto;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class JobApplicationResponseDTO {
	
	private Long applicationId;
	private ApplicationStatus applicationStatus;
	private Long userId;
	private LocalDate appliedDate;
	private String fullName;

}


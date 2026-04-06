package com.icodian.careervia.job.dto;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class JobApplicantListResponseDTO {
	
	private Long applicationId;
	private ApplicationStatus applicationstatus;
	private LocalDate appliedDate;
	private Long userId;
	private Long jobId;
	private String fullName;
	private String jobTitle;

}

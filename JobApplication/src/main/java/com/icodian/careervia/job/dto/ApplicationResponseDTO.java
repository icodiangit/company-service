package com.icodian.careervia.job.dto;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class ApplicationResponseDTO {
	
	private Long applicationId;
	private Long userId;
	private Long jobId;
//	private Long resume_id;
	private LocalDate appliedDate;
	private ApplicationStatus applicationStatus;
	private String remarks;


}

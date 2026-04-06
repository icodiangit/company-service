package com.icodian.careervia.job.dto;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class UserApplicationResponseDTO {
	
	private Long applicationId;
	private String jobTitle;
	private Long jobId;
	private ApplicationStatus applicationStatus;
	private LocalDate appliedDate;
//	private String remark;

}

package com.icodian.careervia.job.dto;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class ApplicationStatusUpdateRequestDTO {
	
	private ApplicationStatus applicationStatus;
	private String remarks;

}

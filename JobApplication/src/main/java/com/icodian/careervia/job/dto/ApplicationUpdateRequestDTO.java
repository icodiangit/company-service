package com.icodian.careervia.job.dto;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import lombok.Data;

@Data
public class ApplicationUpdateRequestDTO {
	
	private ApplicationStatus applicationStatus;
	private String remarks;
	private LocalDate CreatedAt;
	private String message;

}

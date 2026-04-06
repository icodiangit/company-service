package com.icodian.careervia.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ApplicationDTO {
	
	private Long applicationId;
	private Long jobId;
	private String status;
	private Date appliedAt;
	
}

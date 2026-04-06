package com.icodian.careervia.job.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class JobResponseDTO {
	
	private Long jobId;
	private String jobTitle;
	private String description;
	private String location;
	private Integer experience;
	private Double salary;
	private JobType jobType;
	List<String> requiredSkills = new ArrayList<>();
	private LocalDate postedDate;
	private Long companyId;
	private JobStatus jobStatus;
//	private String message;

}

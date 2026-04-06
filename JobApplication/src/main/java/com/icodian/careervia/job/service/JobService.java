package com.icodian.careervia.job.service;

import java.util.List;
import java.util.Optional;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.dto.JobSearchResponseDTO;
import com.icodian.careervia.job.entity.constant.JobType;

public interface JobService {
	
	JobResponseDTO createJob(JobRequestDTO request);
	
	List<JobResponseDTO> getAllJobs();

	Optional<JobResponseDTO> getJobById(Long jobId);

	JobResponseDTO updateJob(Long jobId, JobRequestDTO request);

	String deleteJob(Long jobId);

	List<JobSearchResponseDTO> searchJobs(String jobTitle, String location, JobType jobType, Integer experience, Double salary);
	

}



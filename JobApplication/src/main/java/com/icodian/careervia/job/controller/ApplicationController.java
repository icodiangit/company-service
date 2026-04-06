package com.icodian.careervia.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.job.dto.ApplicationRequestDTO;
import com.icodian.careervia.job.dto.ApplicationResponseDTO;
import com.icodian.careervia.job.dto.ApplicationStatusResponseDTO;
import com.icodian.careervia.job.dto.ApplicationStatusUpdateRequestDTO;
import com.icodian.careervia.job.dto.ApplicationStatusUpdateResponseDTO;
import com.icodian.careervia.job.dto.ApplicationUpdateRequestDTO;
import com.icodian.careervia.job.dto.ApplicationUpdateResponseDTO;
import com.icodian.careervia.job.dto.JobApplicantListResponseDTO;
import com.icodian.careervia.job.dto.JobApplicationResponseDTO;
import com.icodian.careervia.job.dto.UserApplicationResponseDTO;
import com.icodian.careervia.job.entity.constant.ApplicationStatus;
import com.icodian.careervia.job.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	
	@Autowired
	ApplicationService applicationService;
	
	@PostMapping
	public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody ApplicationRequestDTO request) {
		
		ApplicationResponseDTO response = applicationService.createApplication(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/jobs/{jobId}")
	public List<JobApplicationResponseDTO> getApplicationByJobId(@PathVariable ("jobId") Long jobId) {
		
//		JobApplicationResponseDTO response = applicationService.getApplicationByJobId(job_Id);
		return applicationService.getApplicationByJobId(jobId);
		
	}
	
	@GetMapping("/users/{userId}")
	public List<UserApplicationResponseDTO> getApplicationByUserId(@PathVariable ("userId") Long userId) {
		
		return applicationService.getApplicationByUserId(userId);
		
	}
	
	@PatchMapping("/{applicationId}")
	public ResponseEntity<ApplicationUpdateResponseDTO> updateApplication(@PathVariable ("applicationId") Long applicationId,
			@RequestBody ApplicationUpdateRequestDTO request) {
		ApplicationUpdateResponseDTO response = applicationService.updateApplication(applicationId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PatchMapping("/users/{userId}/applications/{applicationId}/withdraw")
	public ResponseEntity<ApplicationStatusResponseDTO> withdrawApplication(@PathVariable ("applicationId") Long applicationId, 
			@PathVariable ("userId") Long userId) {
		
		ApplicationStatusResponseDTO response = applicationService.withdrawApplication(applicationId, userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/jobs/{jobId}/applications")
	public List<JobApplicantListResponseDTO> getApplicationByJobIdAndStatus(@PathVariable ("jobId") Long jobId,
			@RequestParam ApplicationStatus applicationStatus) {
		
		return applicationService.getApplicationByJobIdAndStatus(jobId, applicationStatus);
	}
	
	@PatchMapping("/applications/{applicationId}")
	public ResponseEntity<ApplicationStatusUpdateResponseDTO> updateApplicationStatus(@PathVariable ("applicationId") Long applicationId,
			@RequestBody ApplicationStatusUpdateRequestDTO request) {
		
		ApplicationStatusUpdateResponseDTO response = applicationService.updateApplicationStatus(applicationId, request);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

}



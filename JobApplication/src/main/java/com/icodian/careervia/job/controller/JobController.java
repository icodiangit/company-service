package com.icodian.careervia.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.dto.JobSearchResponseDTO;
import com.icodian.careervia.job.entity.constant.JobType;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.service.JobService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO request){
		
		log.info("Received request to create job: {}", request.getJobTitle());
		
		JobResponseDTO response = jobService.createJob(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping
	public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
		
		List<JobResponseDTO> jobs = jobService.getAllJobs();
		return ResponseEntity.ok(jobs);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobResponseDTO> getJobById(@PathVariable("id") Long job_id) {
		JobResponseDTO response = jobService.getJobById(job_id)
				.orElseThrow(()-> new JobNotFoundException("Job not found with Id: " + job_id));
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<JobResponseDTO> updateJob(@PathVariable("id") Long jobId, @RequestBody JobRequestDTO request) {
		
		JobResponseDTO response = jobService.updateJob(jobId, request);
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable("id") Long job_id) {
		
		String response = jobService.deleteJob(job_id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/jobs/search")
	public List<JobSearchResponseDTO> searchJobs(
			@RequestParam String jobTitle,
			@RequestParam String location,
			@RequestParam JobType jobType,
			@RequestParam Integer experience,
			@RequestParam Double salary) {
		
		return jobService.searchJobs(jobTitle, location, jobType,experience, salary);
	}

}


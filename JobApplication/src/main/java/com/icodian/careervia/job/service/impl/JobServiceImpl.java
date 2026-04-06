package com.icodian.careervia.job.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.dto.JobSearchResponseDTO;
import com.icodian.careervia.job.entity.Company;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.constant.JobType;
import com.icodian.careervia.job.exceptions.InvalidJobDataException;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.JobService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public JobResponseDTO createJob(JobRequestDTO request) {
		// TODO Auto-generated method stub

//		log.info("Creating job with title: {} for company ID: {}", request.getJob_title(), request.getCompany_id());

//		Validate the salary if it is null and throw an exception
		if (request == null) {
			throw new InvalidJobDataException("Job is required");
		}

//		Validate Company exists by calling Company Service
//		CompanyResponseDTO company = companyClient.getCompanyById(request.getCompany_id());
//		log.info("Company validated: {})", company.getCompanyId());

//		company.setCompanyId(request.getCompany_id());

		Company company_info = restTemplate
				.getForObject("http://COMPANY-MICROSERVICE/api/companies/" + request.getCompanyId(), Company.class);
		if (company_info == null) {
			throw new RuntimeException("Company not found");

		}

//		Creating the job entity
		Job job = new Job();

		job.setJobTitle(request.getJobTitle());
		job.setDescription(request.getDescriprtion());
		job.setLocation(request.getLocation());
		job.setExperience(request.getExperience());
		job.setSalary(request.getSalary());
		job.setJobType(request.getJobType());
		job.setRequiredSkills(request.getRequiredSkills());
		job.setPostedDate(request.getPostedDate());
		job.setCompanyId(request.getCompanyId());
		job.setJobStatus(request.getJobStatus());

		Job savedJob = jobRepository.save(job);
		log.info("Job created successfully with job ID: {} ", savedJob.getJobId());

		return mapToResponse(savedJob);
	}

	private JobResponseDTO mapToResponse(Job job) {
		// TODO Auto-generated method stub

		JobResponseDTO response = new JobResponseDTO();
		response.setJobId(job.getJobId());
		response.setJobTitle(job.getJobTitle());
		response.setDescription(job.getDescription());
		response.setLocation(job.getLocation());
		response.setExperience(job.getExperience());
		response.setSalary(job.getSalary());
		response.setJobType(job.getJobType());
		response.setRequiredSkills(job.getRequiredSkills());
		response.setPostedDate(job.getPostedDate());
		response.setCompanyId(job.getCompanyId());
		response.setJobStatus(job.getJobStatus());

		return response;
	}

	@Override
	public List<JobResponseDTO> getAllJobs() {
		// TODO Auto-generated method stub
		return jobRepository.findAll().stream().map(this::mapToListResponse).collect(Collectors.toList());
	}

	private JobResponseDTO mapToListResponse(Job job) {

		JobResponseDTO response = new JobResponseDTO();

		response.setJobId(job.getJobId());
		response.setJobTitle(job.getJobTitle());
		response.setDescription(job.getDescription());
		response.setLocation(job.getLocation());
		response.setExperience(job.getExperience());
		response.setSalary(job.getSalary());
		response.setJobType(job.getJobType());
		response.setRequiredSkills(job.getRequiredSkills());
		response.setPostedDate(job.getPostedDate());
		response.setCompanyId(job.getCompanyId());
		response.setJobStatus(job.getJobStatus());

		return response;
	}

	@Override
	public Optional<JobResponseDTO> getJobById(Long jobId) {
		// TODO Auto-generated method stub

		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new JobNotFoundException("Job not found with id: " + jobId));

		JobResponseDTO dto = new JobResponseDTO();

		dto.setJobId(job.getJobId());
		dto.setJobTitle(job.getJobTitle());
		dto.setDescription(job.getDescription());
		dto.setLocation(job.getLocation());
		dto.setExperience(job.getExperience());
		dto.setSalary(job.getSalary());
		dto.setRequiredSkills(job.getRequiredSkills());
		dto.setPostedDate(job.getPostedDate());
		dto.setCompanyId(job.getCompanyId());
		dto.setJobStatus(job.getJobStatus());
		dto.setJobType(job.getJobType());
		return Optional.ofNullable(dto);

	}

	@Override
	public JobResponseDTO updateJob(Long jobId, JobRequestDTO request) {
		// TODO Auto-generated method stub

//		

		if (request == null) {
			throw new JobNotFoundException("Job is not present.");
		}

		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new JobNotFoundException("Job not found with jobId : " + jobId));

		if (request.getJobTitle() != null) {
			job.setJobTitle(request.getJobTitle());
		}

		if (request.getDescriprtion() != null) {
			job.setDescription(request.getDescriprtion());
		}

		if (request.getLocation() != null) {
			job.setLocation(request.getLocation());
		}

		if (request.getExperience() != null) {
			job.setExperience(request.getExperience());
		}

		if (request.getSalary() != null) {
			job.setSalary(request.getSalary());
		}

		if (request.getJobType() != null) {
			job.setJobType(request.getJobType());
		}

		if (request.getRequiredSkills() != null) {
			job.setRequiredSkills(request.getRequiredSkills());
		}

		if (request.getJobStatus() != null) {
			job.setJobStatus(request.getJobStatus());
		}

		Job updateJob = jobRepository.save(job);

		return mapToUpdateResponse(updateJob);
	}

	private JobResponseDTO mapToUpdateResponse(Job updateJob) {
		// TODO Auto-generated method stub

		JobResponseDTO dto = new JobResponseDTO();

		dto.setJobId(updateJob.getJobId());
		dto.setJobTitle(updateJob.getJobTitle());
		dto.setDescription(updateJob.getDescription());
		dto.setLocation(updateJob.getLocation());
		dto.setExperience(updateJob.getExperience());
		dto.setSalary(updateJob.getSalary());
		dto.setJobType(updateJob.getJobType());
		dto.setRequiredSkills(updateJob.getRequiredSkills());
		dto.setJobStatus(updateJob.getJobStatus());

		return dto;
	}

	@Override
	public String deleteJob(Long job_id) {
		// TODO Auto-generated method stub

//		If job is empty

		if (job_id == null) {
			throw new InvalidJobDataException("Please add your job ID. Job Id is empty.");
		}

		jobRepository.deleteById(job_id);

		return "Job has been deleted";
	}

	@Override
	public List<JobSearchResponseDTO> searchJobs(String jobTitle, String location, JobType jobType, Integer experience,
			Double salary) {
		// TODO Auto-generated method stub

		List<Job> jobs = new ArrayList<>();

		if (jobTitle != null && location != null && jobType != null) {
			jobs = jobRepository.findByJobTitleAndLocationAndJobType(jobTitle, location, jobType);
		}

		else if (jobTitle != null && location == null && jobType == null) {
			jobs = jobRepository.findByJobTitle(jobTitle);
		}

		else if (jobTitle == null && location != null && jobType == null) {
			jobs = jobRepository.findByLocation(location);
		}

		else if (jobTitle == null && location == null && jobType != null) {
			jobs = jobRepository.findByJobType(jobType);
		}

		else if (jobTitle != null && location != null && jobType == null) {
			jobs = jobRepository.findByJobTitleAndLocation(jobTitle, location);
		}

		else if (jobTitle != null && location == null && jobType != null) {
			jobs = jobRepository.findByJobTitleAndJobType(jobTitle, jobType);
		}

		else if (jobTitle == null && location != null && jobType != null) {
			jobs = jobRepository.findByLocationAndJobType(location, jobType);
		}

		else {
			jobs = jobRepository.findAll();
		}

		List<Job> filterJobs = jobs.stream()
				.filter(job -> (experience == null
						|| (job.getExperience() - 1 <= experience && job.getExperience() + 1 >= experience)))
				.filter(job -> (salary == null
						|| (job.getSalary() - 10000 <= salary && job.getSalary() + 10000 >= salary)))
				.collect(Collectors.toList());

		if (filterJobs.isEmpty()) {
			throw new JobNotFoundException("No Jobs found based on the filters.");
		}

		List<JobSearchResponseDTO> response = new ArrayList<>();

		for (Job job : filterJobs) {

			JobSearchResponseDTO dto = new JobSearchResponseDTO();

			dto.setJobId(job.getJobId());
			dto.setCompanyId(job.getCompanyId());
			dto.setExperience(job.getExperience());
			dto.setJobStatus(job.getJobStatus());
			dto.setJobTitle(job.getJobTitle());
			dto.setJobType(job.getJobType());
			dto.setLocation(job.getLocation());
			dto.setPostedDate(job.getPostedDate());
			dto.setRequiredSkills(job.getRequiredSkills());
			dto.setSalary(job.getSalary());

			response.add(dto);
		}

		return response;
	}

}

package com.icodian.careervia.job.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.icodian.careervia.job.entity.Application;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.User;
import com.icodian.careervia.job.entity.constant.ApplicationStatus;
import com.icodian.careervia.job.exceptions.ApplicationNotFoundException;
import com.icodian.careervia.job.exceptions.ApplicationWithdrawNotAllowedException;
import com.icodian.careervia.job.exceptions.InvalidApplicationStatusException;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.repository.ApplicationRepository;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ApplicationResponseDTO createApplication(ApplicationRequestDTO request) {
		// TODO Auto-generated method stub

		if (request == null) {
			throw new ApplicationNotFoundException("Application details are not found.");
		}

		User user_info = restTemplate.getForObject("http://USER-MICROSERVICE/api/users/" + request.getUserId(),
				User.class);
		if (user_info == null) {
			throw new RuntimeException("User not found");
		}

		Job job = jobRepository.findById(request.getJobId())
				.orElseThrow(() -> new JobNotFoundException("Job not found with id: " + request.getJobId()));

//		Create Application
		Application application = new Application();
		application.setUserId(request.getUserId());
		application.setJobId(request.getJobId());
//		application.setResume_id(request.getResume_id());
		application.setAppliedDate(request.getAppliedDate());
		application.setApplicationStatus(request.getApplicationStatus());
		application.setJobTitle(job.getJobTitle());

		Application saveApplication = applicationRepository.save(application);

		return mapToResponse(saveApplication);
	}

	private ApplicationResponseDTO mapToResponse(Application application) {
		// TODO Auto-generated method stub

		ApplicationResponseDTO response = new ApplicationResponseDTO();
		response.setApplicationId(application.getApplicationId());
		response.setUserId(application.getUserId());
		response.setJobId(application.getJobId());
//		response.setResume_id(application.getResume_id());
		response.setAppliedDate(application.getAppliedDate());
		response.setApplicationStatus(application.getApplicationStatus());

		return response;
	}

	@Override
	public List<JobApplicationResponseDTO> getApplicationByJobId(Long jobId) {
		// TODO Auto-generated method stub

//		Job job = new Job();

		List<Application> applications = applicationRepository.findByJobId(jobId);

		if (applications == null) {
			throw new ApplicationNotFoundException("Applications are not found with job Id: " + jobId);
		}

		List<JobApplicationResponseDTO> response = new ArrayList<>();

		for (Application application : applications) {

			User user = restTemplate.getForObject("http://USER-MICROSERVICE/api/users/" + application.getUserId(),
					User.class);

			JobApplicationResponseDTO dto = new JobApplicationResponseDTO();

			dto.setApplicationId(application.getApplicationId());
			dto.setApplicationStatus(application.getApplicationStatus());
			dto.setAppliedDate(application.getAppliedDate());
			dto.setUserId(application.getUserId());
			dto.setFullName(user.getFullName());

			response.add(dto);
		}

		return response;
	}

	@Override
	public List<UserApplicationResponseDTO> getApplicationByUserId(Long userId) {
		// TODO Auto-generated method stub
		
		List<Application> applications = applicationRepository.findByUserId(userId);
		
		if (applications == null) {
			throw new ApplicationNotFoundException("Applications are not found with user Id: " + userId);
		}

		List<UserApplicationResponseDTO> response = new ArrayList<>();
		
		for(Application application :applications) {
			
			UserApplicationResponseDTO dto = new UserApplicationResponseDTO();
			
//			Job job = application.getJob();
			
			dto.setApplicationId(application.getApplicationId());
			dto.setApplicationStatus(application.getApplicationStatus());
			dto.setAppliedDate(application.getAppliedDate());
			dto.setJobId(application.getJobId());
//			dto.setJobTitle(job.setJobTitle(application.getJobTitle()));
			dto.setJobTitle(application.getJobTitle());			
			response.add(dto);
		}
		
		return response;
	}

	@Override
	public ApplicationUpdateResponseDTO updateApplication(Long applicationId, ApplicationUpdateRequestDTO request) {
		// TODO Auto-generated method stub
		if(request == null) {
			throw new InvalidApplicationStatusException("Application data is not send for updation.");
		}
		
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(()-> new ApplicationNotFoundException("Application is not found with applicationId : "+ applicationId));
		
		if(request.getApplicationStatus() != null) {
			application.setApplicationStatus(request.getApplicationStatus());	
			}
		
		if(request.getCreatedAt() != null) {
			application.setCreatedAt(request.getCreatedAt());
			}
		
		if(request.getMessage()	!= null) {
			application.setMessage(request.getMessage());
		}
		
		if(request.getRemarks() != null) {
			application.setRemarks(request.getRemarks());
		}
		
		Application updateApplication = applicationRepository.save(application);
		
		return mapToUpdateResponse(updateApplication);
	}

	private ApplicationUpdateResponseDTO mapToUpdateResponse(Application updateApplication) {
		// TODO Auto-generated method stub
		
		ApplicationUpdateResponseDTO dto = new ApplicationUpdateResponseDTO();
		
		dto.setApplicationId(updateApplication.getApplicationId());
		dto.setApplicationStatus(updateApplication.getApplicationStatus());
		dto.setCreatedAt(updateApplication.getCreatedAt());
		dto.setMessage(updateApplication.getMessage());
		dto.setRemarks(updateApplication.getRemarks());	
		
		return dto;
	}

	@Override
	public ApplicationStatusResponseDTO withdrawApplication(Long applicationId, Long userId) {
		// TODO Auto-generated method stub
		
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(()-> new ApplicationNotFoundException("Application not found with application ID: "+applicationId));
		
		if(!application.getUserId().equals(userId)) {
			throw new ApplicationWithdrawNotAllowedException("Application is not allowed to withdraw.");
		}
		
		application.setApplicationStatus(ApplicationStatus.WITHDRAW);
		
		Application updateApp = applicationRepository.save(application);
		
		ApplicationStatusResponseDTO response = new ApplicationStatusResponseDTO();
		
		response.setApplicationId(updateApp.getApplicationId());
		response.setApplicationStatus(updateApp.getApplicationStatus());
		response.setMessage("Application withdrawn successfully");
		
		return response;
	}

	@Override
	public List<JobApplicantListResponseDTO> getApplicationByJobIdAndStatus(Long jobId,
			ApplicationStatus applicationStatus) {
		// TODO Auto-generated method stub
		
		List<Application> applications = applicationRepository.findByJobId(jobId);
		
		if(applications == null) {
			throw new ApplicationNotFoundException("Application not found with the job ID: "+jobId);
			}
		
		List<Application> filterApplications = applications.stream()
				.filter(app-> app.getApplicationStatus().equals(applicationStatus))
				.collect(Collectors.toList());
		
		if(filterApplications.isEmpty()) {
			throw new ApplicationNotFoundException("Application not found with the application status as: "+ applicationStatus);
		}
		
		List<JobApplicantListResponseDTO> response = new ArrayList<>();
		
		for(Application application : filterApplications) {
			
			JobApplicantListResponseDTO dto = new JobApplicantListResponseDTO();
			
			User user = restTemplate.getForObject("http://USER-MICROSERVICE/api/users/" + application.getUserId(),
					User.class);

			dto.setApplicationId(application.getApplicationId());
			dto.setApplicationstatus(application.getApplicationStatus());
			dto.setAppliedDate(application.getAppliedDate());
			dto.setJobTitle(application.getJobTitle());
			dto.setJobId(application.getJobId());
			dto.setUserId(application.getUserId());
			dto.setFullName(user.getFullName());
			
			response.add(dto);
					
			}
		
			
		return response;
	}

	@Override
	public ApplicationStatusUpdateResponseDTO updateApplicationStatus(Long applicationId,
			ApplicationStatusUpdateRequestDTO request) {
		// TODO Auto-generated method stub
		
		if(request == null) {
			throw new InvalidApplicationStatusException("Application status is not send for updation.");
		}
		
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(()-> new ApplicationNotFoundException("Application not found having application ID: "+ applicationId));
		
		if(application.getApplicationStatus()==ApplicationStatus.WITHDRAW) {
			throw new InvalidApplicationStatusException("Application has already been withdrawn.");
		}
		
		if(application.getApplicationStatus() != null ) {
			application.setApplicationStatus(request.getApplicationStatus());
		}
		
		if(application.getRemarks() != null) {
			application.setRemarks(request.getRemarks());		
		}
		
		Application updateApplicationStatus = applicationRepository.save(application);
		
		
		return mapToUpdateStatus(updateApplicationStatus);
	}

	private ApplicationStatusUpdateResponseDTO mapToUpdateStatus(Application updateApplicationStatus) {
		// TODO Auto-generated method stub
		
		
		ApplicationStatusUpdateResponseDTO dto = new ApplicationStatusUpdateResponseDTO();
				
		dto.setApplicationId(updateApplicationStatus.getApplicationId());
		dto.setApplicationStatus(updateApplicationStatus.getApplicationStatus());
		dto.setRemarks(updateApplicationStatus.getRemarks());
		
		return dto;
	}

}

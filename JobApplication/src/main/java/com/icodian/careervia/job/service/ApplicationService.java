package com.icodian.careervia.job.service;

import java.util.List;

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

public interface ApplicationService {

	ApplicationResponseDTO createApplication(ApplicationRequestDTO request);

	List<JobApplicationResponseDTO> getApplicationByJobId(Long jobId);
	
	List<UserApplicationResponseDTO> getApplicationByUserId(Long userId);

	ApplicationUpdateResponseDTO updateApplication(Long applicationId, ApplicationUpdateRequestDTO request);

	ApplicationStatusResponseDTO withdrawApplication(Long applicationId, Long userId);

	List<JobApplicantListResponseDTO> getApplicationByJobIdAndStatus(Long jobId, ApplicationStatus applicationStatus);

	ApplicationStatusUpdateResponseDTO updateApplicationStatus(Long applicationId,
			ApplicationStatusUpdateRequestDTO request);

}

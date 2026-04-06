package com.icodian.careervia.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.constant.JobType;

public interface JobRepository extends JpaRepository<Job, Long>{

	List<Job> findByJobTitle(String jobTitle);

	List<Job> findByJobTitleAndLocationAndJobType(String jobTitle, String location, JobType jobType);

	List<Job> findByLocation(String location);

	List<Job> findByJobTitleAndLocation(String jobTitle, String location);

	List<Job> findByJobType(JobType jobType);

	List<Job> findByJobTitleAndJobType(String jobTitle, JobType jobType);

	List<Job> findByLocationAndJobType(String location, JobType jobType);

/*	@Query("SELECT j FROM Job j WHERE"+
	"(:job_status IS NULL OR j.job_status = :job_status) AND"+
			"(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND" +
			"(:companyName IS NULL OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', companyName, '%'))) AND" +
			"(:job_type IS NULL OR j.job_type = :job_type) AND" +
			"(:experience IS NULL OR j.experience = :experience)"
			)
	List<Job> findJobsByFilters(@Param("job_status") JobStatus jobStatus,
			@Param("location") String location,
			@Param("companyName") String companyName,
			@Param("job_type") JobType jobType,
			@Param("experience") String experience
			);
	
	Optional<Job> findById(Long job_id);
*/
}

package com.icodian.careervia.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.job.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

	List<Application> findByJobId(Long jobId);
	
	List<Application> findByUserId(Long userId);

}

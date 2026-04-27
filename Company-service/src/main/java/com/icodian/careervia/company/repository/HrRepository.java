package com.icodian.careervia.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.company.entity.Hr;

import java.util.List;

public interface HrRepository extends JpaRepository<Hr, Long> {

    List<Hr> findByCompanyCompanyId(Long companyId);
}
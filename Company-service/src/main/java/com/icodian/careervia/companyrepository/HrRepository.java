package com.icodian.careervia.companyrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.companyentity.Hr;

import java.util.List;

public interface HrRepository extends JpaRepository<Hr, Long> {

    List<Hr> findByCompanyCompanyId(Long companyId);
}
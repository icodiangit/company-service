package com.icodian.careervia.companyrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.companyentity.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email);

    boolean existsByEmail(String email);
}
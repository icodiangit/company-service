package com.icodian.careervia.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.company.entity.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email);

    boolean existsByEmail(String email);
}
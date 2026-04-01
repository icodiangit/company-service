package com.icodian.careervia.company.service;

import com.icodian.careervia.company.dto.CompanyDTO;
import java.util.List;

public interface CompanyService {

    CompanyDTO createCompany(CompanyDTO dto);

    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO updateCompany(Long id, CompanyDTO dto);

    void deleteCompany(Long id);
}
package com.icodian.careervia.company.service;

import com.icodian.careervia.company.dto.CompanyDTO;
import java.util.List;

public interface CompanyService {

    CompanyDTO createCompany(CompanyDTO dto);

    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO updateCompany(Long id, CompanyDTO dto);

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setIndustry(dto.getIndustry());
        company.setLocation(dto.getLocation());
        company.setWebsite(dto.getWebsite());
        company.setDescription(dto.getDescription());
        company.setPassword(dto.getPassword());

        // If status is provided use it, otherwise default to ACTIVE
        company.setStatus(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE);

        company.setCreatedAt(LocalDate.now());

        return mapToDTO(repository.save(company));
    }

    // ✅ GET ALL
    public List<CompanyDTO> getAllCompanies() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    public CompanyDTO getCompanyById(Long id) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToDTO(company);
    }

    // ✅ UPDATE (Status Added Here)
    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {

        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setIndustry(dto.getIndustry());
        company.setLocation(dto.getLocation());
        company.setWebsite(dto.getWebsite());
        company.setDescription(dto.getDescription());

        // ✅ Update Status also
        if (dto.getStatus() != null) {
            company.setStatus(dto.getStatus());
        }

        return mapToDTO(repository.save(company));
    }

    // ✅ MAPPER
    private CompanyDTO mapToDTO(Company c) {

        CompanyDTO dto = new CompanyDTO();

        dto.setCompanyId(c.getCompanyId());
        dto.setCompanyName(c.getCompanyName());
        dto.setEmail(c.getEmail());
        dto.setIndustry(c.getIndustry());
        dto.setLocation(c.getLocation());
        dto.setStatus(c.getStatus());
        dto.setWebsite(c.getWebsite());
        dto.setDescription(c.getDescription());
        dto.setPassword(c.getPassword());
        
        return dto;
    }

    void deleteCompany(Long id);

}
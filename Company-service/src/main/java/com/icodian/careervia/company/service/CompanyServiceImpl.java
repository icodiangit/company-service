package com.icodian.careervia.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.icodian.careervia.company.dto.CompanyDTO;
import com.icodian.careervia.company.entity.Company;
import com.icodian.careervia.company.repository.CompanyRepository;
import com.icodian.careervia.companyenum.Status;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Override
    public CompanyDTO createCompany(CompanyDTO dto) {

        Company company = new Company();

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setIndustry(dto.getIndustry());
        company.setLocation(dto.getLocation());
        company.setWebsite(dto.getWebsite());
        company.setDescription(dto.getDescription());

        company.setStatus(dto.getStatus() != null ? dto.getStatus() : Status.ACTIVE);

        company.setCreatedAt(LocalDate.now());

        return mapToDTO(repository.save(company));
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {

        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {

        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setIndustry(dto.getIndustry());
        company.setLocation(dto.getLocation());
        company.setWebsite(dto.getWebsite());
        company.setDescription(dto.getDescription());

        if (dto.getStatus() != null) {
            company.setStatus(dto.getStatus());
        }

        return mapToDTO(repository.save(company));
    }

    @Override
    public void deleteCompany(Long id) {

        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        repository.delete(company);
    }

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

        return dto;
    }
}
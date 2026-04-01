package com.icodian.careervia.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.icodian.careervia.company.dto.HrDTO;
import com.icodian.careervia.company.entity.Company;
import com.icodian.careervia.company.entity.Hr;
import com.icodian.careervia.company.repository.CompanyRepository;
import com.icodian.careervia.company.repository.HrRepository;
import com.icodian.careervia.companyenum.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HrServiceImpl implements HrService {

    private final HrRepository repository;
    private final CompanyRepository companyRepository;

    @Override
    public HrDTO createHr(HrDTO dto) {

        Hr hr = new Hr();

        // ✅ BASIC FIELDS
        hr.setName(dto.getName());
        hr.setEmail(dto.getEmail());
        hr.setPhone(dto.getPhone());

        // ✅ NEW FIELDS
        hr.setPassword(dto.getPassword());
        hr.setCompanyName(dto.getCompanyName());
        hr.setWebsite(dto.getWebsite());
        hr.setLocation(dto.getLocation());
        hr.setIndustry(dto.getIndustry());
        hr.setDesignation(dto.getDesignation());
        hr.setDescription(dto.getDescription());

        // ✅ STATUS
        hr.setStatus(convertToStatus(dto.getStatus()));

        // ✅ OPTIONAL COMPANY (no error if null)
        if (dto.getCompanyId() != null) {
            Company company = companyRepository.findById(dto.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            hr.setCompany(company);
        }

        return mapToDTO(repository.save(hr));
    }

    @Override
    public List<HrDTO> getAllHrs() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HrDTO getHrById(Long id) {
        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));
        return mapToDTO(hr);
    }

    @Override
    public HrDTO updateHrStatus(Long id, String status) {

        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));

        hr.setStatus(convertToStatus(status));

        return mapToDTO(repository.save(hr));
    }

    @Override
    public HrDTO updateHr(Long id, HrDTO dto) {

        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));

        // ✅ BASIC FIELDS
        hr.setName(dto.getName());
        hr.setEmail(dto.getEmail());
        hr.setPhone(dto.getPhone());

        // ✅ NEW FIELDS
        hr.setPassword(dto.getPassword());
        hr.setCompanyName(dto.getCompanyName());
        hr.setWebsite(dto.getWebsite());
        hr.setLocation(dto.getLocation());
        hr.setIndustry(dto.getIndustry());
        hr.setDesignation(dto.getDesignation());
        hr.setDescription(dto.getDescription());

        // ✅ STATUS
        hr.setStatus(convertToStatus(dto.getStatus()));

        // ✅ COMPANY UPDATE
        if (dto.getCompanyId() != null) {
            Company company = companyRepository.findById(dto.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            hr.setCompany(company);
        }

        return mapToDTO(repository.save(hr));
    }

    @Override
    public void deleteHr(Long id) {

        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));

        repository.delete(hr);
    }

    private Status convertToStatus(String status) {
        if (status == null) {
            return Status.ACTIVE;
        }
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
    }

    private HrDTO mapToDTO(Hr hr) {

        HrDTO dto = new HrDTO();

        dto.setHrId(hr.getHrId());    
        dto.setName(hr.getName());
        dto.setEmail(hr.getEmail());
        dto.setPhone(hr.getPhone());

        // ✅ NEW FIELDS
        dto.setPassword(hr.getPassword());
        dto.setCompanyName(hr.getCompanyName());
        dto.setWebsite(hr.getWebsite());
        dto.setLocation(hr.getLocation());
        dto.setIndustry(hr.getIndustry());
        dto.setDesignation(hr.getDesignation());
        dto.setDescription(hr.getDescription());

        dto.setStatus(hr.getStatus().name());

        if (hr.getCompany() != null) {
            dto.setCompanyId(hr.getCompany().getCompanyId());
        }

        return dto;
    }
}
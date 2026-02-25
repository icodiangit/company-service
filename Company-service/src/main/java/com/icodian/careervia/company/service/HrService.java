package com.icodian.careervia.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.icodian.careervia.companydto.HrDTO;
import com.icodian.careervia.companyentity.Company;
import com.icodian.careervia.companyentity.Hr;
import com.icodian.careervia.companyenum.Status;
import com.icodian.careervia.companyrepository.HrRepository;
import com.icodian.careervia.companyrepository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HrService {

    private final HrRepository repository;
    private final CompanyRepository companyRepository;

    //  CREATE
    public HrDTO createHr(HrDTO dto) {

        Hr hr = new Hr();
        hr.setName(dto.getName());
        hr.setEmail(dto.getEmail());
        hr.setPhone(dto.getPhone());

        
        hr.setStatus(convertToStatus(dto.getStatus()));

        if (dto.getCompanyId() == null) {
            throw new RuntimeException("Company ID is required");
        }

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        hr.setCompany(company);

        return mapToDTO(repository.save(hr));
    }

    // GET ALL
    public List<HrDTO> getAllHrs() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //  GET BY ID
    public HrDTO getHrById(Long id) {
        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));
        return mapToDTO(hr);
    }

    //  UPDATE STATUS
    public HrDTO updateHrStatus(Long id, String status) {

        Hr hr = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HR not found"));

        hr.setStatus(convertToStatus(status));

        return mapToDTO(repository.save(hr));
    }

    //  ENUM SAFE CONVERSION METHOD
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

    //  MAPPER
    private HrDTO mapToDTO(Hr hr) {

        HrDTO dto = new HrDTO();
        dto.setHrId(hr.getHrId());
        dto.setName(hr.getName());
        dto.setEmail(hr.getEmail());
        dto.setPhone(hr.getPhone());
        dto.setStatus(hr.getStatus().name());

        if (hr.getCompany() != null) {
            dto.setCompanyId(hr.getCompany().getCompanyId());
        }

        return dto;
    }
}
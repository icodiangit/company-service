package com.icodian.careervia.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.icodian.careervia.company.service.CompanyService;
import com.icodian.careervia.companydto.CompanyDTO;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    //  CREATE
    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO dto) {
        return service.createCompany(dto);
    }

    // GET ALL
    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return service.getAllCompanies();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable Long id) {
        return service.getCompanyById(id);
    }

    // UPDATE 
    @PutMapping("/{id}")   
    public CompanyDTO updateCompany(@PathVariable Long id,
                                    @RequestBody CompanyDTO dto) {
        return service.updateCompany(id, dto);
    }
}
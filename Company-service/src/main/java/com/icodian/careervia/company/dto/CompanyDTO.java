package com.icodian.careervia.company.dto;

import com.icodian.careervia.companyenum.Status;
import lombok.Data;

@Data
public class CompanyDTO {

    private Long companyId;
    private String companyName;
    private String email;
    private String industry;
    private String location;
    private String website;
    private String description;
    private String password;
    private Status status;
}
package com.icodian.careervia.company.dto;

import lombok.Data;

@Data
public class HrDTO {

    private Long hrId;
    private String name;
    private String email;
    private String phone;

    private String password;        // ADD
    private String companyName;     // ADD
    private String website;         // ADD
    private String location;        // ADD
    private String industry;        // ADD
    private String designation;     // ADD
    private String description;     // ADD

    private String status;
    private Long companyId;
}
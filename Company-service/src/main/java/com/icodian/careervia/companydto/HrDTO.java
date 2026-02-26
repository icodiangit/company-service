package com.icodian.careervia.companydto;

import lombok.Data;

@Data
public class HrDTO {
    private Long hrId;
    private String name;
    private String email;
    private String phone;
    private String status;     
    private Long companyId;
}
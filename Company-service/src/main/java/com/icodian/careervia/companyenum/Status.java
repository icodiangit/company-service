package com.icodian.careervia.companyenum;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

	@JsonProperty("active")
    ACTIVE,
    
    @JsonProperty("inactive")
    INACTIVE;
    
    
  
}
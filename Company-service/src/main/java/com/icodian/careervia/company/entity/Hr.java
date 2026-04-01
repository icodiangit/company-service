package com.icodian.careervia.company.entity;

import com.icodian.careervia.companyenum.Status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "HR")
@Data
public class Hr {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_seq")
    @SequenceGenerator(name = "hr_seq", sequenceName = "HR_SEQ", allocationSize = 1)
    @Column(name = "HR_ID")
    private Long hrId;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "EMAIL", length = 120)
    private String email;

    @Column(name = "PHONE", length = 10)
    private String phone;
    
    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "COMPANY_NAME", length = 150)
    private String companyName;

    @Column(name = "WEBSITE", length = 150)
    private String website;

    @Column(name = "LOCATION", length = 150)
    private String location;

    @Column(name = "INDUSTRY", length = 100)
    private String industry;

    @Column(name = "DESIGNATION", length = 100)
    private String designation;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    // Many HRs -> one Company (Foreign Key)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = true)
    private Company company;

	//public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	//public Object getStatus() {
		// TODO Auto-generated method stub
		//return null;
	


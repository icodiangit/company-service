package com.icodian.careervia.company.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.icodian.careervia.companyenum.Status;

@Entity
@Table(name = "COMPANY")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "COMPANY_SEQ", allocationSize = 1)
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;

    @Column(name = "EMAIL", length = 120)
    private String email;
    
    @Column(name = "password", nullable = false)
	private String password;

    @Column(name = "INDUSTRY", length = 80)
    private String industry;

    @Column(name = "LOCATION", length = 150)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Column(name = "WEBSITE", length = 150)
    private String website;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Hr> hrs;
}
package com.icodian.careervia.companyentity;

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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    // Many HRs -> one Company (Foreign Key)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

	//public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	//public Object getStatus() {
		// TODO Auto-generated method stub
		//return null;
	


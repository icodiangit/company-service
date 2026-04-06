package com.icodian.careervia.user.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Resume")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resume_id", updatable = false, nullable = false)
	private Long resumeId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "resume_type")
	private String resumeType;
	
	@Column(name = "drive_file_id", unique = true)
	private String driveFileId;
	
	@Column(name = "derive_file_path")
	private String deriveFilePath;
	
	@Column(name = "template_name")
	private String templateName;
	
	@Column(name = "version_number")
	private Integer versionNumber;
	
	@Column(name = "uploaded_by")
	private String uploadedBy;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
}

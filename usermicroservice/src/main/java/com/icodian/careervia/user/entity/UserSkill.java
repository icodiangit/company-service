package com.icodian.careervia.user.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UserSkill")
public class UserSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_skill_id", nullable = false, updatable = false)
	private Long userSkillId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@Column(name = "proficiency_level")
	private String proficiencyLevel;
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "last_updated")
	private Date lastUpdated;

}

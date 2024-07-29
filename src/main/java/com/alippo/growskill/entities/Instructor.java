package com.alippo.growskill.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "instructors")
@Data
public class Instructor extends User{

	@Column(name = "specialization")
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	@Column(name = "qualification")
	private String qualification;

}

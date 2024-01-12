package com.alippo.growskill.entities;

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
@Table(name = "instructor")
@Data
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instructor_id")
	private Integer id;

	@Column(name = "instructor_name")
	private String instructorName;

	@Column(name = "specialization")
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	@Column(name = "phone_number",unique = true)
	private String phoneNumber;

	@Column(name = "email",unique = true)
	private String email;

	@Column(name = "password")
	private String password;
	
    @CreationTimestamp
    @Column(name="Date_of_Creation")
    private Date creationDateAndTime;
    
    @Column(name="Last_loggedIn")
    private Date lastLoggedIn;

}

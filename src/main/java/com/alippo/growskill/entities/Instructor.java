<<<<<<< HEAD
package com.alippo.growskill.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(name="Date of Creation")
    private Date creationDateAndTime;
    
    @Column(name="Last logged In")
    private Date lastLoggedIn;

}
=======
package com.alippo.growskill.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(name="Date of Creation")
    private Date creationDateAndTime;
    
    @Column(name="Last logged In")
    private Date lastLoggedIn;

}
>>>>>>> e037054c09bb80704634214ea70a18e881577576

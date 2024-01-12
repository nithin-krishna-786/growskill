package com.alippo.growskill.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
    private Integer id;
	
	@Column(name="student_name")
    private String studentName;
	
	@Column(name="email",unique = true)
    private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_number",unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")		
    private List<Enrollment> enrollments;	
    
    @CreationTimestamp
    @Column(name="date_of_creation")
    private Date creationDateAndTime;
    
    @Column(name="last_logged_In")
    private Date lastLoggedIn;
    
    @Column(name="verified")
    private Boolean verified;
    
    @Column(name="passcode")
    private String passCode;
}

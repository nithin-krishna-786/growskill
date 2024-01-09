<<<<<<< HEAD
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
    private int id;
	
	@Column(name="student_name")
	@NotEmpty
    private String studentName;
	
	@Column(name="email",unique = true)
	@NotEmpty
	@Email(message = "Should be in a Proper email format")
    private String email;
	
	@Column(name="password")
	@NotEmpty
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	@Column(name="phone_number",unique = true)
	@Size(min = 10, message = "Phone number must be at least 10 Digits long")
	@NotEmpty
    private String phoneNumber;

    @OneToMany(mappedBy = "student")		
    private List<Enrollment> enrollments;	
    
    @CreationTimestamp
    @Column(name="Date of Creation")
    private Date creationDateAndTime;
    
    @Column(name="Last logged In")
    private Date lastLoggedIn;
}
=======
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
    private int id;
	
	@Column(name="student_name")
	@NotEmpty
    private String studentName;
	
	@Column(name="email",unique = true)
	@NotEmpty
	@Email(message = "Should be in a Proper email format")
    private String email;
	
	@Column(name="password")
	@NotEmpty
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	@Column(name="phone_number",unique = true)
	@NotEmpty
    private String phoneNumber;

    @OneToMany(mappedBy = "student")		
    private List<Enrollment> enrollments;	
    
    @CreationTimestamp
    @Column(name="Date of Creation")
    private Date creationDateAndTime;
    
    @Column(name="Last logged In")
    private Date lastLoggedIn;
}
>>>>>>> e037054c09bb80704634214ea70a18e881577576

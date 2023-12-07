package com.alippo.growskill.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
    private int id;
	
	@Column(name="student_name")
    private String studentName;
	
	@Column(name="email")
    private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "student")		
    private List<Enrollment> enrollments;	
}

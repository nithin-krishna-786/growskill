package com.alippo.growskill.dto;

import java.util.List;

import com.alippo.growskill.entities.Enrollment;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class StudentDTO {
	private int id;
	private String studentName;
	private String email;
	private String password;
	private String phoneNumber;
	private List<EnrollmentDTO> enrollments;
}

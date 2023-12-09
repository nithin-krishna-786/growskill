package com.alippo.growskill.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentDTO {
	private int id;
	
//	@NotEmpty
	private String studentName;
	
//	@NotEmpty
	@Email(message = "Should be in a Proper email format")
	private String email;
	
//	@NotEmpty
//	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	@NotEmpty
	@Digits(integer = 10, fraction = 0, message = "Phone number must be numeric and have up to 10 digits.")
	private String phoneNumber;
	
//	private List<EnrollmentDTO> enrollments;
	
}

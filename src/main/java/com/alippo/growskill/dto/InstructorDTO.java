package com.alippo.growskill.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstructorDTO {

	private int instructorID;

	@NotEmpty
	private String instructorName;

	@NotEmpty
	@Enumerated(EnumType.STRING)
	private String specialization;

	@NotEmpty
	@Digits(integer = 10, fraction = 0, message = "Phone number must be numeric and have up to 10 digits.")
	private String phoneNumber;

	@NotEmpty
	@Email(message = "Should be in a Proper email format")
	private String email;
	
	@NotEmpty
	@Size(min = 6, message = "Password must be at least 6 characters long")
	@Pattern.List(
		   {@Pattern(regexp = ".*[a-z].*", message = "Password must contain at least 1 lowercase letter"),
			@Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least 1 uppercase letter"),
			@Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\].*", message = "Password must contain at least 1 special character") })
	private String password;
}



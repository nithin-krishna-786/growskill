package com.alippo.growskill.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
}

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

	private Long id;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String username;
	
	@NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@NotEmpty
	@Email(message = "Should be in a Proper email format")
	private String email;
	
	@NotEmpty
	@Digits(integer = 10, fraction = 0, message = "Phone number must be numeric and have up to 10 digits.")
	private String phoneNumber;
	
	private AddressDTO address;

	@NotEmpty
	@Enumerated(EnumType.STRING)
	private String specialization;
	
	@NotEmpty
	private String qualification;
}



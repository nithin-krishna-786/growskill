
package com.alippo.growskill.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentRequestDTO {

	@NotEmpty
	private String studentName;

	@NotEmpty
	private String userName;

	@NotEmpty
	@Email(message = "Should be in a Proper email format")
	private String email;

	@NotEmpty
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@NotEmpty
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
	@Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
	private String phoneNumber;

	@NotEmpty(message = "Street should not be empty")
	private String street;

	@NotEmpty(message = "City should not be empty")
	private String city;

	@NotEmpty(message = "State should not be empty")
	private String state;

	@NotEmpty(message = "Country should not be empty")
	private String country;

	@NotEmpty(message = "ZipCode should not be empty")
    @Pattern(regexp = "\\d{6}", message = "Zip code must be 6 digits")
	private String zipCode;

}

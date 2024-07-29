package com.alippo.growskill.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDTO {

	private Long id;
	
	@NotEmpty(message = "Name should be non-empty")
	private String name;
	
	@NotEmpty(message = "Username should be non-empty")
	private String username;
	
	@NotEmpty(message = "Email should be non-empty")
	@Email(message = "Should be in a proper email format")
	private String email;
	
	@NotEmpty(message = "Password should be non-empty")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\]).*$", 
    message = "Password must contain at least 1 lowercase letter, 1 uppercase letter, and 1 special character")
	private String password;
	
	@NotEmpty(message = "Phonenumber should be non-empty")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
	private String phoneNumber;

	private Set<RoleDTO> roles;
	
	private LocalDateTime creationDateAndTime;

	private Boolean verified;

	private String passCode;
	
}

package com.alippo.growskill.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.alippo.growskill.entities.Address;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Role;
import com.alippo.growskill.entities.Student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentUpdateDTO {
	
	@NotEmpty(message = "Name should not be empty")
	private String name;

	@NotEmpty(message = "User name should not be empty")
	private String username;

	@NotEmpty(message = "Password should not be empty")
	private String password;

	@Email(message = "Email should be Proper")
	@NotEmpty(message = "Email should not be empty")
	private String email;
	
	@NotEmpty(message = "Phone number should not be empty")
    @Size(min = 12, max = 12, message = "Phone number must be 12 characters long")
	private String phoneNumber;
	
	@NotEmpty(message = "street should not be empty")
	private String street;
	
	@NotEmpty(message = "city should not be empty")
	private String city;
	
	@NotEmpty(message = "state should not be empty")
	private String state;
	
	@NotEmpty(message = "country should not be empty")
	private String country;
	
	@NotEmpty(message = "zipCode should not be empty")
	private String zipCode;

	@NotEmpty(message = "College should not be empty")
	private String college;

	@NotEmpty(message = "Location should not be empty")
	private String location;
}

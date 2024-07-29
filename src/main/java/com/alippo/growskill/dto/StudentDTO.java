
package com.alippo.growskill.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentDTO extends UserDTO{
	private List<EnrollmentDTO> enrollments;
	
	@NotEmpty(message = "College should be non-empty")
	private String college;
	
	private LocalDateTime creationDateAndTime;
	private LocalDateTime lastLoggedIn;
	private Boolean verified;
	private String passCode;
}

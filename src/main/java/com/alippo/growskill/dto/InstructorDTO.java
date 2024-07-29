package com.alippo.growskill.dto;

import java.time.LocalDateTime;


import com.alippo.growskill.entities.Specialization;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class InstructorDTO extends UserDTO{
	
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

    private String qualification;
}



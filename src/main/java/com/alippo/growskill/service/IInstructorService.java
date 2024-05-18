package com.alippo.growskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.enums.Specialization;

@Service
public interface IInstructorService {

	InstructorDTO createInstructor(InstructorDTO instructorDTO);
	List<Instructor> getAllInstructors();
	Instructor getInstructorById(int instructorID);
	List<Instructor> getInstructorsBySpecialization(String specialization);
	InstructorDTO updateInstructor(int instructorID, InstructorDTO instructorDTO);
	void deleteInstructor(int instructorID);

}

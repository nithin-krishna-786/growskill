package com.alippo.growskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

@Service
public interface IInstructorService {

	Instructor createInstructor(Instructor instructor);
	List<Instructor> getAllInstructors();
	Instructor getInstructorById(int instructorID);
	List<Instructor> getInstructorsBySpecialization(String specialization);
	Instructor updateInstructor(int instructorID, Instructor updatedInstructor);
	void deleteInstructor(int instructorID);
	Instructor logIn(String username,String password);

}

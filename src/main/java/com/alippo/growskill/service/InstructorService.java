package com.alippo.growskill.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.exceptions.InstructorNotFoundException;
import com.alippo.growskill.exceptions.StudentNotFoundException;
import com.alippo.growskill.repository.InstructorRepository;

@Service
public class InstructorService implements IInstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Instructor createInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}

	public List<Instructor> getAllInstructors() {
		return instructorRepository.findAll();
	}

	public Instructor getInstructorById(int instructorID) {
		Optional<Instructor> instructor = instructorRepository.findById(instructorID);

		if (instructor.isPresent())
			return instructor.get();
		else
			throw new InstructorNotFoundException("Instructor not found for given id:" + instructorID);
	}

	public Instructor updateInstructor(int instructorID, Instructor updatedInstructor) {
		Optional<Instructor> existingInstructor = instructorRepository.findById(instructorID);

		if (existingInstructor.isPresent()) {
			Instructor instructorToUpdate = existingInstructor.get();
			instructorToUpdate.setInstructorName(updatedInstructor.getInstructorName());
			instructorToUpdate.setSpecialization(updatedInstructor.getSpecialization());

			return instructorRepository.save(instructorToUpdate);
		} else {
			throw new InstructorNotFoundException("Instructor not found for given id:" + instructorID);
		}
	}

	public void deleteInstructor(int instructorID) {

		Instructor instructor = instructorRepository.findById(instructorID).orElseThrow(
				() -> new InstructorNotFoundException("Instructor not found for given id:" + instructorID));
		instructorRepository.delete(instructor);
	}

	@Override
	public List<Instructor> getInstructorsBySpecialization(String specialization) {
		Specialization spec = Specialization.valueOf(specialization);
		List<Instructor> instructors = instructorRepository.findBySpecialization(spec);
		return instructors;
	}

	@Override
	public Instructor logIn(String email, String password) {

		Instructor instructor = instructorRepository.findByEmailAndPassword(email,password)
				.orElseThrow(() -> new InstructorNotFoundException(
						String.format("Instructor Not Found with given email:%s and passsword:%s", email, password)));


		Date loggedDateAndTime = new Date();
		instructor.setLastLoggedIn(loggedDateAndTime);
		instructor = instructorRepository.save(instructor);
		return instructor;
	}

}

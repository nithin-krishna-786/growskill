package com.alippo.growskill.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Role;
import com.alippo.growskill.entities.Specialization;
import com.alippo.growskill.exceptions.InstructorNotFoundException;
import com.alippo.growskill.repository.InstructorRepository;
import com.alippo.growskill.repository.RoleRepository;
import com.alippo.growskill.util.Constants;

@Service
public class InstructorService implements IInstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Instructor createInstructor(Instructor instructor) {

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_INSTRUCTOR");
		roles.add(userRole);

		instructor.setRoles(roles);

		instructor.setVerified(false);

		int passcode = generatePasscode(Constants.PASSCODE_LENGTH);
		instructor.setPassCode(String.valueOf(passcode));

		return instructorRepository.save(instructor);
	}

	public static int generatePasscode(int length) {
		Random random = new Random();
		int passcode = random.nextInt((int) Math.pow(10, length));
		return passcode;
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
			instructorToUpdate.setName(updatedInstructor.getName());
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

		Instructor instructor = instructorRepository.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new InstructorNotFoundException(
						String.format("Instructor Not Found with given email:%s and passsword:%s", email, password)));

		instructor = instructorRepository.save(instructor);
		return instructor;
	}

}

package com.alippo.growskill.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alippo.growskill.dto.AddressDTO;
import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Address;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.User;
import com.alippo.growskill.enums.Specialization;
import com.alippo.growskill.exceptions.InstructorNotFoundException;
import com.alippo.growskill.repository.InstructorRepository;

@Service
public class InstructorService implements IInstructorService {

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private ModelMapper modelMapper;

//	public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
//		
//		AddressDTO addressDTO = instructorDTO.getAddress();
//		
//		Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
//		
//		//CONVERT addressDTO to address
//		Address address = modelMapper.map(addressDTO, Address.class);
//		
//		instructor.setAddress(address);
//		
//		//Save instructor
//		instructor = instructorRepository.save(instructor);
//		
//		instructorDTO = modelMapper.map(instructor, InstructorDTO.class);
//		return instructorDTO;
//	}
	
	public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
		
		AddressDTO addressDTO = instructorDTO.getAddress();
		
		Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
		
		User user = (User) instructor;
		
		//CONVERT addressDTO to address
		Address address = modelMapper.map(addressDTO, Address.class);
		
		instructor.setAddress(address);
		
		//Save instructor
		instructor = instructorRepository.save(instructor);
		
		instructorDTO = modelMapper.map(instructor, InstructorDTO.class);
		return instructorDTO;
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

	public InstructorDTO updateInstructor(int instructorID, InstructorDTO instructorDTO) {
		
		Instructor instructor = instructorRepository.findById(instructorID).orElseThrow(
				() -> new InstructorNotFoundException("Instructor not found for given id:" + instructorID));

		instructor = modelMapper.map(instructorDTO, Instructor.class);
		instructor =  instructorRepository.save(instructor);
		
		instructorDTO = modelMapper.map(instructor,InstructorDTO.class);
		return instructorDTO;
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


}

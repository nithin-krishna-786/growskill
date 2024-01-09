package com.alippo.growskill.mapper;

import java.util.List;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

public class MapperClass {

	public static Instructor mapDTOToEntity(InstructorDTO instructorDTO) {
		Instructor instructor = new Instructor();
		instructor.setId(instructor.getId());
		instructor.setInstructorName(instructorDTO.getInstructorName());
		instructor.setEmail(instructorDTO.getEmail());
		instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
		Specialization specialization = Specialization.valueOf(instructorDTO.getSpecialization().toUpperCase());
		instructor.setSpecialization(specialization);
		return instructor;
	}

	public static InstructorDTO mapEntityToDTO(Instructor instructor) {
		InstructorDTO instructorDTO = new InstructorDTO();
		instructorDTO.setInstructorID(instructor.getId());
		instructorDTO.setInstructorName(instructor.getInstructorName());
		instructorDTO.setPhoneNumber(instructor.getPhoneNumber());
		instructorDTO.setEmail(instructor.getEmail());
		instructorDTO.setSpecialization(instructor.getSpecialization().name());
		return instructorDTO;
	}

	public static List<InstructorDTO> mapEntityListToDTOList(List<Instructor> instructors) {
		return instructors.stream().map(MapperClass::mapEntityToDTO).toList();
	}

}

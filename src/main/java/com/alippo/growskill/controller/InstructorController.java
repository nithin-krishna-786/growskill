package com.alippo.growskill.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;
import com.alippo.growskill.mapper.MapperClass;
import com.alippo.growskill.service.InstructorService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/instructor")
	public ResponseEntity<InstructorDTO> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO) {
		Instructor instructor = modelMapper.map(instructorDTO,Instructor.class);
		Instructor createdInstructor = instructorService.createInstructor(instructor);
		InstructorDTO result = modelMapper.map(createdInstructor,InstructorDTO.class);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/instructor")
	public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
		List<Instructor> allInstructors = instructorService.getAllInstructors();
		List<InstructorDTO> result = MapperClass.mapEntityListToDTOList(allInstructors);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{instructorID}")
	public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable("instructorID") int id) {
		Instructor instructor = instructorService.getInstructorById(id);
		InstructorDTO instructorDTO = MapperClass.mapEntityToDTO(instructor);
		return ResponseEntity.ok(instructorDTO);

	}

	@PutMapping("/{instructorID}")
	public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable int instructorID,
			@RequestBody InstructorDTO instructorDTO) {
		Instructor updatedInstructor = instructorService.updateInstructor(instructorID,
				MapperClass.mapDTOToEntity(instructorDTO));

		return new ResponseEntity<>(MapperClass.mapEntityToDTO(updatedInstructor), HttpStatus.OK);
	}

	@DeleteMapping("/{instructorID}")
	public ResponseEntity<String> deleteInstructor(@PathVariable("instructorID") int id) {
		instructorService.deleteInstructor(id);
		return ResponseEntity.ok("Instructor deleted successfully");
	}

	
}

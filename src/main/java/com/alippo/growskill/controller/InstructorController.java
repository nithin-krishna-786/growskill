package com.alippo.growskill.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;
import com.alippo.growskill.entities.Student;
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
	public ResponseEntity<Instructor> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO) {
		Instructor instructor = modelMapper.map(instructorDTO,Instructor.class);
		Instructor result = instructorService.createInstructor(instructor);
		
		if(result != null)
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/instructor")
	public ResponseEntity<List<Instructor>> getAllInstructors() {
		List<Instructor> allInstructors = instructorService.getAllInstructors();
		
		if(allInstructors != null)
			return new ResponseEntity<>(allInstructors, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{instructorID}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("instructorID") int id) {
		Instructor instructor = instructorService.getInstructorById(id);
		return ResponseEntity.ok(instructor);

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

	@GetMapping("/login")
	public ResponseEntity<Instructor> logIn(String username,String password)
	{
		Instructor instructor = instructorService.logIn(username, password);
		
		if(instructor != null)
			return new ResponseEntity<>(instructor,HttpStatus.OK);
		else
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}

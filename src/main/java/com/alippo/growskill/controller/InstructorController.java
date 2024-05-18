package com.alippo.growskill.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.service.InstructorService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<InstructorDTO> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO) {
		instructorDTO = instructorService.createInstructor(instructorDTO);
		return new ResponseEntity<>(instructorDTO, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Instructor>> getAllInstructors() {
		List<Instructor> allInstructors = instructorService.getAllInstructors();
		return new ResponseEntity<>(allInstructors, HttpStatus.OK);
	}

	@GetMapping("/{instructorID}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("instructorID") int id) {
		Instructor instructor = instructorService.getInstructorById(id);
		return ResponseEntity.ok(instructor);
	}

	@PutMapping("/{instructorID}")
	public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable int instructorID,
			@RequestBody InstructorDTO instructorDTO) {
		InstructorDTO updatedInstructor = instructorService.updateInstructor(instructorID, instructorDTO);

		return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
	}

	@DeleteMapping("/{instructorID}")
	public ResponseEntity<String> deleteInstructor(@PathVariable("instructorID") int id) {
		instructorService.deleteInstructor(id);
		return ResponseEntity.ok("Instructor deleted successfully");
	}

}

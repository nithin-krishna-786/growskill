package com.alippo.growskill.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.service.InstructorService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ModelMapper modelMapper;

//	@PostMapping
//	public ResponseEntity<?> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO,
//			BindingResult bindingResult) {
//
//		// THIS CODE PROVIDES ERROR MESSAGES AT INDIVIDUAL ATTRIBUTE LEVEL
//		Boolean errorExists = false;
//
//		Map<String, String> errors = new HashMap<>();
//		if (bindingResult.hasErrors()) {
//			errorExists = true;
//			bindingResult.getFieldErrors().forEach(error -> {
//				errors.put(error.getField(), error.getDefaultMessage());
//			});
//			return ResponseEntity.badRequest().body(errors);
//		}
//
//		Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
//		Instructor result = instructorService.createInstructor(instructor);
//		instructorDTO = modelMapper.map(result, InstructorDTO.class);
//
//		return new ResponseEntity<>(instructorDTO, HttpStatus.CREATED);
//	}

	@GetMapping("/all")
	public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
		List<Instructor> allInstructors = instructorService.getAllInstructors();

		List<InstructorDTO> instructorDTOs = allInstructors.stream()
				.map(instructor -> modelMapper.map(instructor, InstructorDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(instructorDTOs);
	}

	@GetMapping("/{instructorID}")
	public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable("instructorID") int id) {
		Instructor instructor = instructorService.getInstructorById(id);
		InstructorDTO result = modelMapper.map(instructor, InstructorDTO.class);
		return ResponseEntity.ok(result);

	}

//	@PutMapping("/{instructorID}")
//	public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable int instructorID,
//			@RequestBody InstructorDTO instructorDTO) {
//
//		Instructor updatedInstructor = instructorService.updateInstructor(instructorID,
//				MapperClass.mapDTOToEntity(instructorDTO));
//
//		return new ResponseEntity<>(MapperClass.mapEntityToDTO(updatedInstructor), HttpStatus.OK);
//	}

	@DeleteMapping("/{instructorID}")
	public ResponseEntity<String> deleteInstructor(@PathVariable("instructorID") int id) {
		instructorService.deleteInstructor(id);
		return ResponseEntity.ok("Instructor deleted successfully");
	}

//	@GetMapping("/login")
//	public ResponseEntity<Instructor> logIn(String username, String password) {
//		Instructor instructor = instructorService.logIn(username, password);
//
//		if (instructor != null)
//			return new ResponseEntity<>(instructor, HttpStatus.OK);
//		else
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testAPI()
	{
		return new ResponseEntity<>("Test API Working",HttpStatus.OK);
	}

}

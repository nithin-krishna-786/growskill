package com.alippo.growskill.controller;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.dto.JwtAuthResponse;
import com.alippo.growskill.dto.LoginDTO;
import com.alippo.growskill.dto.StudentDTO;
import com.alippo.growskill.dto.UserDTO;
import com.alippo.growskill.service.AuthService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@PostMapping("/student/register")
	public ResponseEntity<?> registerStudent(@RequestBody @Valid StudentDTO studentDTO,
			BindingResult bindingResult) {
		
		// THIS CODE PROVIDES ERROR MESSAGES AT INDIVIDUAL ATTRIBUTE LEVEL

		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}
		
		
		StudentDTO result = authService.registerAsStudent(studentDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping("/instructor/register")
	public ResponseEntity<?> registerInstructor(@RequestBody @Valid InstructorDTO instructorDTO,
			BindingResult bindingResult) {
		
		// THIS CODE PROVIDES ERROR MESSAGES AT INDIVIDUAL ATTRIBUTE LEVEL

		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}
		
		InstructorDTO response = authService.registerAsInstructor(instructorDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/admin/register")
	public ResponseEntity<?> registerAdmin(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
		
		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}
		
		UserDTO result = authService.registerAsAdmin(userDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	// Build Login REST API
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto) {
		String token = authService.login(loginDto);

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);

		return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		// Get the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Perform any additional cleanup or logging out logic here

		// Invalidate the current user's session
		SecurityContextHolder.clearContext();
		// You might want to clear cookies or tokens here, depending on your
		// authentication mechanism

		return ResponseEntity.ok("Logout successful");
	}
}

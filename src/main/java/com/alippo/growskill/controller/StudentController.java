package com.alippo.growskill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alippo.growskill.dto.CertificateDTO;
import com.alippo.growskill.dto.EnrollmentDTO;
import com.alippo.growskill.dto.RecordingDTO;
import com.alippo.growskill.dto.StudentDTO;
import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.PaymentStatus;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.service.IStudentService;
import com.alippo.growskill.util.Constants;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/register")
	public ResponseEntity<StudentDTO> registerStudent(@RequestBody @Valid StudentDTO studentDTO) {
		Student student = modelMapper.map(studentDTO, Student.class);
		Student registeredStudent = studentService.registerStudent(student);
		StudentDTO result = modelMapper.map(registeredStudent, StudentDTO.class);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping("/enroll/{studentId}/{courseId}")
	public ResponseEntity<EnrollmentDTO> enrollInCourse(@PathVariable Integer studentId, @PathVariable Integer courseId,
			@RequestParam PaymentStatus paymentStatus) {
		Enrollment enrolledCourse = studentService.enrollInCourse(studentId, courseId, paymentStatus);
		EnrollmentDTO enrollmentDTO = modelMapper.map(enrolledCourse, EnrollmentDTO.class);
		return new ResponseEntity<>(enrollmentDTO, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Student> studentLogin(String email, String password) {
		Student student = studentService.login(email, password);

		if (student != null)
			return new ResponseEntity<>(student, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PostMapping("/attendClass/{enrollmentId}/{classId}")
	public ResponseEntity<String> attendClass(@PathVariable Integer enrollmentId, @PathVariable Integer classId)
			throws ClassInCourseNotFoundException {
		Enrollment enrollment = studentService.getEnrollmentById(enrollmentId);
		ClassInCourse classToAttend = studentService.getClassById(classId, enrollmentId);
		String zoomLink = studentService.attendClass(enrollment, classToAttend);
		return new ResponseEntity<>(zoomLink, HttpStatus.OK);
	}

	@GetMapping("/download-certificate/{enrollmentId}")
	public ResponseEntity<CertificateDTO> downloadCertificate(@PathVariable Integer enrollmentId) {
		Enrollment enrollment = studentService.getEnrollmentById(enrollmentId);
		Certificate certificate = studentService.downloadCertificate(enrollment);
		CertificateDTO certificateDTO = modelMapper.map(certificate, CertificateDTO.class);
		return new ResponseEntity<>(certificateDTO, HttpStatus.OK);
	}

	@GetMapping("/download-recordings/{enrollmentId}")
	public ResponseEntity<List<RecordingDTO>> downloadRecordings(@PathVariable Integer enrollmentId) {
		Enrollment enrollment = studentService.getEnrollmentById(enrollmentId);
		List<Recording> recordings = studentService.downloadRecordings(enrollment);
		List<RecordingDTO> recordingDTOs = recordings.stream()
				.map(recording -> modelMapper.map(recording, RecordingDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(recordingDTOs, HttpStatus.OK);
	}

	@PatchMapping("/verify-student")
	public ResponseEntity<String> validatePasscode(@RequestParam String email,
			@RequestParam String passcode) {

		boolean verified = studentService.validatePasscode(email, passcode);

		if (verified) {
			return ResponseEntity.ok("Student verified successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Passcode");
		}
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> requestForgotPassword(@RequestParam String email) {

		String passcode = studentService.generatePasscode(Constants.PASSCODE_LENGTH);
		boolean passcodeSaved = studentService.savePasscode(email, passcode);

		if (passcodeSaved) {
			return ResponseEntity.ok("Passcode sent successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate and save passcode");
		}
	}

	@PatchMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String email,
			@RequestParam String newPassword, @RequestParam String newPasscode) {

		boolean passcodeValid = studentService.validatePasscode(email, newPasscode);

		if (passcodeValid) {
			boolean passwordUpdated = studentService.updatePassword(email, newPassword);
			if (passwordUpdated) {
				return ResponseEntity.ok("Password updated successfully");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Invalid Old Password, CAN'T reset");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid current passcode");
		}
	}

}

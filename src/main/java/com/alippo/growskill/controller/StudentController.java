package com.alippo.growskill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alippo.growskill.dto.CreateStudentRequestDTO;
import com.alippo.growskill.dto.ForgotPasswordDTO;
import com.alippo.growskill.dto.PasswordResetDTO;
import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.entities.User;
import com.alippo.growskill.enums.PaymentStatus;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.exceptions.PasswordException;
import com.alippo.growskill.repository.UserRepository;
import com.alippo.growskill.security.AuthService;
import com.alippo.growskill.service.IStudentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private AuthService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<Student> registerStudent(@RequestBody @Valid CreateStudentRequestDTO createStudentRequestDTO) {
		Student registeredStudent = studentService.registerStudent(createStudentRequestDTO);
		return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
	}

	@PostMapping("/enroll/{studentId}/{courseId}")
	public ResponseEntity<Enrollment> enrollInCourse(@PathVariable Integer studentId, @PathVariable Integer courseId,
			@RequestParam PaymentStatus paymentStatus) {
		Enrollment enrolledCourse = studentService.enrollInCourse(studentId, courseId, paymentStatus);
		return new ResponseEntity<>(enrolledCourse, HttpStatus.CREATED);
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
	public ResponseEntity<Certificate> downloadCertificate(@PathVariable Integer enrollmentId) {
		Enrollment enrollment = studentService.getEnrollmentById(enrollmentId);
		Certificate certificate = studentService.downloadCertificate(enrollment);
		return new ResponseEntity<>(certificate, HttpStatus.OK);
	}

	@GetMapping("/download-recordings/{enrollmentId}")
	public ResponseEntity<List<Recording>> downloadRecordings(@PathVariable Integer enrollmentId) {
		Enrollment enrollment = studentService.getEnrollmentById(enrollmentId);
		List<Recording> recordings = studentService.downloadRecordings(enrollment);
		return new ResponseEntity<>(recordings, HttpStatus.OK);
	}

	@PatchMapping("/verify-student")
	public ResponseEntity<String> validatePasscode(@RequestParam String email, @RequestParam String passcode) {

		boolean verified = studentService.validatePasscode(email, passcode);

		if (verified) {
			return ResponseEntity.ok("Student verified successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Passcode");
		}
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> requestForgotPassword(@RequestParam(required = true) String email) {

//		String passcode = studentService.generatePasscode(Constants.PASSCODE_LENGTH);
//		studentService.savePasscode(email, passcode);
		studentService.forgotPassword(email);
		return ResponseEntity.ok("Email sent successfully for Forgot Password Reset");
	}

	@PostMapping("/forgot-password-submit")
	public ResponseEntity<String> forgotPasswordSubmission(ForgotPasswordDTO forgotPasswordDTO) {

//		String passcode = studentService.generatePasscode(Constants.PASSCODE_LENGTH);
//		studentService.savePasscode(email, passcode);
//		studentService.updatePasscodeAndEmailWithLink(email);
//		return ResponseEntity.ok("Email sent successfully for Password Reset");

		studentService.forgotPasswordSubmision(forgotPasswordDTO);
		return ResponseEntity.ok("Password updated successfully");
	}

	@PutMapping("/reset-password")
	public ResponseEntity<User> resetPassword(PasswordResetDTO passwordResetDTO) {

		String email = passwordResetDTO.getEmail();
		String oldPassword = passwordResetDTO.getOldPassword();
		String newPassword = passwordResetDTO.getNewPassword();
		String reenterNewPassword = passwordResetDTO.getReenterNewPassword();
		
		if(!newPassword.equals(reenterNewPassword))
			throw new PasswordException("New Password and Reenter Password are not the same");

		User user = authService.findByEmail(email);

		Boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());

		if (!matches)
			throw new PasswordException("Password mismatch");

		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user = userRepository.save(user);
		return ResponseEntity.ok(user);
	}

}

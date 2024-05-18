package com.alippo.growskill.controller;

import com.alippo.growskill.dto.CertificateDTO;
import com.alippo.growskill.dto.ClassInCourseDTO;
import com.alippo.growskill.dto.CourseDTO;
import com.alippo.growskill.dto.InstructorCourseAssignmentDTO;
import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.enums.Specialization;
import com.alippo.growskill.exceptions.CourseNotFoundException;
import com.alippo.growskill.exceptions.EnrollmentNotFoundException;
import com.alippo.growskill.repository.EnrollmentRepository;
import com.alippo.growskill.service.AdminService;
import com.alippo.growskill.service.CourseService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@PostMapping("/certificate/{enrollmentID}")
	public ResponseEntity<CertificateDTO> createCertificate(@PathVariable("enrolmentID") Integer id) {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);

		if (enrollment.isEmpty())
			throw new EnrollmentNotFoundException("Enrollment Not Found");

		Certificate certificate = adminService.createCertificate(enrollment.get());
		CertificateDTO certificateDTO = modelMapper.map(certificate, CertificateDTO.class);
		return new ResponseEntity<>(certificateDTO, HttpStatus.CREATED);

	}

	@PostMapping("/course")
	public ResponseEntity<CourseDTO> createCourse(@RequestParam Specialization specialization,
			@RequestParam int numberOfClasses) {
		Course course = adminService.createCourse(specialization, numberOfClasses);
		CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
		return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
	}

	@PostMapping("/class/{courseID}")
	public ResponseEntity<ClassInCourseDTO> createClassInCourse(@PathVariable("courseID") Integer courseID,
			@RequestBody ClassInCourseDTO classInCourseDTO) {

		Course course = courseService.getCourseById(courseID)
				.orElseThrow(() -> new CourseNotFoundException("CourseNotFound"));
		ClassInCourse classInCourse = modelMapper.map(classInCourseDTO, ClassInCourse.class);
		ClassInCourse result = adminService.createClassInCourse(classInCourse,course);
		
		if(result != null)
		{
			ClassInCourseDTO resultDTO = modelMapper.map(result, ClassInCourseDTO.class);
			return new ResponseEntity<ClassInCourseDTO>(resultDTO,HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/assign-instructor-to-course")
	public ResponseEntity<String> assignInstructorToCourse(
			@RequestBody InstructorCourseAssignmentDTO instructorCourseAssignmentDTO) {
		Course assignedCourse = adminService.assignInstructorToCourse(instructorCourseAssignmentDTO.getInstructorID(),
				instructorCourseAssignmentDTO.getCourseID());
		return new ResponseEntity<>("Instructor assigned to course with ID: " + assignedCourse.getId(), HttpStatus.OK);
	}
 
}

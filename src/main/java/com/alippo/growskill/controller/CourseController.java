package com.alippo.growskill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alippo.growskill.dto.CourseDTO;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.service.CourseService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/course")
	public List<CourseDTO> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		List<CourseDTO> courseDTOs = new ArrayList<>();
		CourseDTO courseDTO = new CourseDTO();
		for (Course course : courses) {
			courseDTO = modelMapper.map(course, CourseDTO.class);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable int id) {
		Optional<Course> course = courseService.getCourseById(id);
		
		if(course.isPresent())
		{	
			CourseDTO courseDTO = modelMapper.map(course.get(), CourseDTO.class);
			return new ResponseEntity<>(courseDTO, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Course Not Found for ID:"+id,HttpStatus.NOT_FOUND);
	}

	@PostMapping("/course")
	public ResponseEntity<?> saveCourse(@RequestBody CourseDTO courseDTO) {
		Course course = modelMapper.map(courseDTO,Course.class);
		Course savedCourse = courseService.saveCourse(course);
		if(savedCourse != null)
		{	
			CourseDTO savedCourseDTO = modelMapper.map(savedCourse, CourseDTO.class);
			return new ResponseEntity<>(savedCourseDTO, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>("Failed to Create Course", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id) {
		Optional<Course> course = courseService.getCourseById(id);
		if (course.isPresent()) {
			courseService.deleteCourse(id);
			return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
		}
	}
}

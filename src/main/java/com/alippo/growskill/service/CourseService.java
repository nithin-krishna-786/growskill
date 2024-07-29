package com.alippo.growskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.Course;
import com.alippo.growskill.exceptions.CourseNotFoundException;
import com.alippo.growskill.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(int id) {

		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("No Course with given id" + id));
		return course;
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public void deleteCourse(int id) {

		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("No Course to delete with given id" + id));
		
		courseRepository.delete(course);
	}
}

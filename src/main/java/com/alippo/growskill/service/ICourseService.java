package com.alippo.growskill.service;

import java.util.List;
import java.util.Optional;

import com.alippo.growskill.entities.Course;

public interface ICourseService {
	List<Course> getAllCourses();
	public Optional<Course> getCourseById(int id);
	public Course saveCourse(Course course);
	public void deleteCourse(int id);
}

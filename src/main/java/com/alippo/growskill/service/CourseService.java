package com.alippo.growskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.Course;
import com.alippo.growskill.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService{
	
	@Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}


package com.alippo.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;

@Repository
public interface ClassInCourseRepository extends JpaRepository<ClassInCourse, Integer> {

}

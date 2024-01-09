package com.alippo.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alippo.growskill.entities.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}

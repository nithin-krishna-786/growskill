package com.alippo.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alippo.growskill.entities.Course;

@Repository
public interface RecordingRepository extends JpaRepository<Course, Integer> {

}
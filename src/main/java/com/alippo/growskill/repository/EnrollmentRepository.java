package com.alippo.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.Enrollment;

@Repository 
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

}

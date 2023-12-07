package com.alippo.growskill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	List<Instructor> findBySpecialization(Specialization speciallization);
}

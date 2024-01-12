
package com.alippo.growskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Optional<Student> findByEmailAndPassword(String email, String password);
	
	Student findByEmail(String email);
	
}
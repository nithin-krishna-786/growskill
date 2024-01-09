<<<<<<< HEAD
package com.alippo.growskill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	List<Instructor> findBySpecialization(Specialization speciallization);

	Optional<Instructor> findByEmailAndPassword(String email, String password);
}
=======
package com.alippo.growskill.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	List<Instructor> findBySpecialization(Specialization speciallization);

	Optional<Instructor> findByEmailAndPassword(String email, String password);
}
>>>>>>> e037054c09bb80704634214ea70a18e881577576

package com.alippo.growskill.repositories;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.enums.Specialization;
import com.alippo.growskill.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindBySpecialization() {
        // Given
        Instructor instructor1 = new Instructor();
        instructor1.setSpecialization(Specialization.BAKING);
        entityManager.persistAndFlush(instructor1);

        Instructor instructor2 = new Instructor();
        instructor2.setSpecialization(Specialization.HANDICRAFTS);
        entityManager.persistAndFlush(instructor2);

        // When
        List<Instructor> instructors = instructorRepository.findBySpecialization(Specialization.BAKING);

        // Then
        assertThat(instructors).hasSize(1);
        assertThat(instructors.get(0).getSpecialization()).isEqualTo(Specialization.BAKING);
    }

    @Test
    public void testFindByEmailAndPassword() {
        // Given
        String email = "instructor@example.com";
        String password = "password123";

        Instructor instructor = new Instructor();
        instructor.setEmail(email);
        instructor.setPassword(password);
        entityManager.persistAndFlush(instructor);

        // When
        Optional<Instructor> foundInstructor = instructorRepository.findByEmailAndPassword(email, password);

        // Then
        assertThat(foundInstructor).isPresent();
        assertThat(foundInstructor.get().getEmail()).isEqualTo(email);
        assertThat(foundInstructor.get().getPassword()).isEqualTo(password);
    }

    @Test
    public void testFindByEmailAndPassword_NotFound() {
        // Given
        String email = "nonexistent@example.com";
        String password = "nonexistentpassword";

        // When
        Optional<Instructor> foundInstructor = instructorRepository.findByEmailAndPassword(email, password);

        // Then
        assertThat(foundInstructor).isEmpty();
    }
}

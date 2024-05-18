package com.alippo.growskill.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.enums.Specialization;
import com.alippo.growskill.exceptions.InstructorNotFoundException;
import com.alippo.growskill.repository.CertificateRepository;
import com.alippo.growskill.repository.CourseRepository;
import com.alippo.growskill.repository.InstructorRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

	@Mock
	private CertificateRepository certificateRepository;

	@Mock
	private CourseRepository courseRepository;
	
	@Mock
	private InstructorRepository instructorRepository;

	@InjectMocks
	private AdminService adminService;

	@Test
	public void testcreateCertificate() {
		// Create a mock Enrollment
		Enrollment enrollment = new Enrollment();
		// Create a mock Certificate
		Certificate certificate = new Certificate();
		certificate.setEnrollment(enrollment);
		certificate.setDownloadLink("DOWNLOAD LINK");

		// Mock the behavior of the certificateRepository
		when(certificateRepository.save(Mockito.any(Certificate.class))).thenReturn(certificate);

		// Call the method to be tested
		Certificate result = adminService.createCertificate(enrollment);

		// Verify that the save method was called with the correct parameter
		verify(certificateRepository, times(1)).save(any(Certificate.class));

		// Assert the result
		assertNotNull(result);
		assertEquals(enrollment, result.getEnrollment());
		assertEquals("DOWNLOAD LINK", result.getDownloadLink());
	}

	@Test
	public void testCreateCourse() {
		// Create a mock Specialization
		Specialization specialization = Specialization.BAKING;
		int numberOfClasses = 5;

		// Create a mock Course
		Course course = new Course();
		course.setSpecialization(specialization);
		course.setNumberOfClasses(numberOfClasses);

		// Mock the behavior of the courseRepository
		when(courseRepository.save(any(Course.class))).thenReturn(course);

		// Call the method to be tested
		Course result = adminService.createCourse(specialization, numberOfClasses);

		// Verify that the save method was called with the correct parameter
		verify(courseRepository, times(1)).save(any(Course.class));

		// Assert the result
		assertNotNull(result);
		assertEquals(specialization, result.getSpecialization());
		assertEquals(numberOfClasses, result.getNumberOfClasses());
	}
	
	@Test
	public void testAssignInstructorToCourse()
	{
		Integer instructorId = 1;
		Integer courseID =1; 
		
		Instructor instructor = new Instructor();
		instructor.setId(instructorId);
		
		Course course = new Course();
		course.setId(courseID);
		
		Mockito.when(instructorRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(instructor));
		Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		
		Mockito.when(courseRepository.save(course)).thenReturn(course);

		Course result = adminService.assignInstructorToCourse(instructorId,courseID);
		
		assertNotNull(result);
		assertEquals(result.getId(), courseID);
	}
	
	  @Test
	    public void testCreateClassInCourse() {
	        // Create a mock Course
	        Course course = new Course();

	        // Create a mock ClassInCourse
	        ClassInCourse classInCourse = new ClassInCourse();
	        classInCourse.setTopic("Test Topic");
	        
	        Recording recording = new Recording();
	        recording.setRecordingLink("RecordingLink");
			classInCourse.setRecording(recording);

	        // Mock the behavior of the courseRepository
	        when(courseRepository.save(any(Course.class))).thenReturn(course);

	        // Call the method to be tested
	        ClassInCourse result = adminService.createClassInCourse(classInCourse, course);

	        // Verify that save method was called with the correct parameter
	        verify(courseRepository, times(1)).save(course);

	        // Assert the result
	        assertNotNull(result);
	        assertEquals(classInCourse.getTopic(), result.getTopic());
	    }
	

}

package com.alippo.growskill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Specialization;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.exceptions.CourseNotFoundException;
import com.alippo.growskill.repository.CertificateRepository;
import com.alippo.growskill.repository.ClassInCourseRepository;
import com.alippo.growskill.repository.CourseRepository;
import com.alippo.growskill.repository.InstructorRepository;

public class AdminService implements IAdminService {

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private ClassInCourseRepository classInCourseRepository;

	@Override
	public Certificate createCertificate(Enrollment enrollment) {
		Certificate certificate = new Certificate();
		certificate.setEnrollment(enrollment);
		certificate.setDownloadLink("DOWNLOAD LINK");
		Certificate result = certificateRepository.save(certificate);
		return result;
	}

	@Override
	public Course createCourse(Specialization specialization, int numberOfClasses) {
		Course course = new Course();
		course.setSpecialization(specialization);
		course.setNumberOfClasses(numberOfClasses);
		return courseRepository.save(course);
	}

	@Override
	public Course assignInstructorToCourse(Integer instructorId, Integer courseID) {
		Optional<Instructor> instructor = instructorRepository.findById(instructorId);
		Optional<Course> course = courseRepository.findById(courseID);
		course.get().setInstructor(instructor.get());
		Course result = courseRepository.save(course.get());
		return result;
	}

	@Transactional
	@Override
	public ClassInCourse createClassInCourse(ClassInCourse classInCourse, Course course) {
		course.addClassInCourse(classInCourse);

		Recording recording = new Recording();
		recording.setClassRelatedToRecording(classInCourse);
		recording.setRecordingLink(classInCourse.getRecording().getRecordingLink());

		classInCourse.setRecording(recording);
		course.addClassInCourse(classInCourse);

		Course result = courseRepository.save(course);

		Optional<ClassInCourse> res =  result.getClassList().stream()
							.filter(c -> c.getTopic().equals(classInCourse.getTopic()))
							.findFirst();

		return res.get();
	}

}

package com.alippo.growskill.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.CompletionStatus;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.PaymentStatus;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.exceptions.CourseNotFoundException;
import com.alippo.growskill.exceptions.EnrollmentNotFoundException;
import com.alippo.growskill.exceptions.PaymentNotDoneException;
import com.alippo.growskill.exceptions.StudentNotFoundException;
import com.alippo.growskill.repository.ClassInCourseRepository;
import com.alippo.growskill.repository.CourseRepository;
import com.alippo.growskill.repository.EnrollmentRepository;
import com.alippo.growskill.repository.StudentRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private ClassInCourseRepository classInCourseRepository;

	@Autowired
	private AdminService adminService;

	private Validator validator;

	public StudentService() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	@Override
	public Student registerStudent(Student student) {

		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		
		if (!violations.isEmpty()) {
			throw new IllegalArgumentException("Validation error: " + violations.iterator().next().getMessage());
		}

		return studentRepository.save(student);
	}

	@Override
	public Enrollment enrollInCourse(Integer StudentID, Integer courseID, PaymentStatus paymentStatus) {
		Student student = studentRepository.findById(StudentID)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));

		Course course = courseRepository.findById(courseID)
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		Enrollment enrollment = new Enrollment();
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		enrollment.setNumberOfClassesCompleted(0);
		enrollment.setCompletionStatus(CompletionStatus.NOT_COMPLETED);
		enrollment.setCertificate(null);

		if (paymentStatus == PaymentStatus.PAID || paymentStatus == PaymentStatus.HALF_PAID)
			enrollment.setPaymentStatus(paymentStatus);
		else
			throw new PaymentNotDoneException("Payment is not Done");

		return enrollmentRepository.save(enrollment);

	}


	@Override
	public String attendClass(Enrollment enrollment, ClassInCourse classToAttend) {
		enrollment.setNumberOfClassesCompleted(enrollment.getNumberOfClassesCompleted() + 1);

		if (enrollment.getNumberOfClassesCompleted().equals(enrollment.getCourse().getNumberOfClasses())) {
			enrollment.setCompletionStatus(CompletionStatus.COMPLETED);
			enrollment.setEligibleToDownload(true);
			Certificate certificate = adminService.createCertificate(enrollment);
			enrollment.setCertificate(certificate);
		}

		enrollmentRepository.save(enrollment);
		return classToAttend.getZoomLink();
	}

	@Override
	public Certificate downloadCertificate(Enrollment enrollment) {
		if (enrollment.getCompletionStatus().equals(CompletionStatus.COMPLETED))
			return enrollment.getCertificate();
		else
			return null;
	}

	@Override
	public List<Recording> downloadRecordings(Enrollment enrollment) {
		if (enrollment.getCompletionStatus().equals(CompletionStatus.COMPLETED)) {
			List<ClassInCourse> classList = enrollment.getCourse().getClassList();
			List<Recording> recordingsList = new ArrayList<>();
			for (ClassInCourse classInTheList : classList) {
				Recording recording = classInTheList.getRecording();
				recordingsList.add(recording);
			}
			return recordingsList;
		}
		return null;
	}

	@Override
	public Enrollment getEnrollmentById(Integer enrollmentId) {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);

		if (enrollment.isPresent())
			return enrollment.get();
		else
			throw new EnrollmentNotFoundException("EnrollmentNotFound");
	}

	@Override
	public ClassInCourse getClassById(Integer classId, Integer enrollmentId) throws ClassInCourseNotFoundException {

		Optional<ClassInCourse> classFound = classInCourseRepository.findById(classId);

		if (classFound.isPresent())
			return classFound.get();
		else
			throw new ClassInCourseNotFoundException("Class Not Found");

	}

	@Override
	public Student login(String email, String password) {
		Student student = studentRepository.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new StudentNotFoundException(
						String.format("Student Not Found with given email:%s and passsword:%s", email, password)));

		Date loggedDateAndTime = new Date();
		student.setLastLoggedIn(loggedDateAndTime);
		student = studentRepository.save(student);
		return student;
	}

}

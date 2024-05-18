package com.alippo.growskill.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alippo.growskill.dto.CreateStudentRequestDTO;
import com.alippo.growskill.dto.ForgotPasswordDTO;
import com.alippo.growskill.entities.Address;
import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.entities.User;
import com.alippo.growskill.enums.CompletionStatus;
import com.alippo.growskill.enums.PaymentStatus;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.exceptions.CourseNotFoundException;
import com.alippo.growskill.exceptions.CredentialsException;
import com.alippo.growskill.exceptions.EnrollmentNotFoundException;
import com.alippo.growskill.exceptions.PasswordException;
import com.alippo.growskill.exceptions.PaymentNotDoneException;
import com.alippo.growskill.exceptions.StudentNotFoundException;
import com.alippo.growskill.exceptions.UserNotFoundException;
import com.alippo.growskill.repository.ClassInCourseRepository;
import com.alippo.growskill.repository.CourseRepository;
import com.alippo.growskill.repository.EnrollmentRepository;
import com.alippo.growskill.repository.StudentRepository;
import com.alippo.growskill.repository.UserRepository;
import com.alippo.growskill.util.Constants;

import jakarta.transaction.Transactional;
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
	private UserRepository userRepository;

	@Autowired
	private ClassInCourseRepository classInCourseRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Validator validator;

	public StudentService() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
	


	@Override
	public Student registerStudent(CreateStudentRequestDTO studentDTO) {

		// check username is already exists in database
		if (userRepository.existsByUsername(studentDTO.getUserName())) {
			throw new CredentialsException(HttpStatus.BAD_REQUEST, "Username already exists!");
		}

		// check email is already exists in database
		if (userRepository.existsByEmail(studentDTO.getEmail())) {
			throw new CredentialsException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
		}

		Student student = new Student();
		student.setName(studentDTO.getStudentName());
		student.setUsername(studentDTO.getUserName());
		student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
		student.setEmail(studentDTO.getEmail());
		student.setPhoneNumber(studentDTO.getPhoneNumber());

		Address address = new Address();
		address.setStreet(studentDTO.getStreet());
		address.setCity(studentDTO.getCity());
		address.setState(studentDTO.getState());
		address.setCountry(studentDTO.getCountry());
		address.setZipCode(studentDTO.getZipCode());

		student.setAddress(address);
		address.setUser(student);

		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		if (!violations.isEmpty()) {
			throw new IllegalArgumentException("Validation error: " + violations.iterator().next().getMessage());
		}

		LocalDateTime creationDateAndTime = LocalDateTime.now();
		student.setCreationDateAndTime(creationDateAndTime);

		student.setVerified(false);
		String passcode = generatePasscode(Constants.PASSCODE_LENGTH);
		student.setPassCode(passcode);

		student = studentRepository.save(student);

		sendOutEmailWithPasscode(student);

		return student;
	}

	private void sendOutEmailWithPasscode(Student student) {
		String emailVerificationBody = Constants.PASSCODE + student.getPassCode();
		emailSenderService.sendSimpleEmail(student.getEmail(), emailVerificationBody,
				Constants.EMAIL_VERIFICATION_SUBJECT);
	}

	@Override
	public Enrollment enrollInCourse(Integer studentID, Integer courseID, PaymentStatus paymentStatus) {
		Student student = studentRepository.findById(studentID)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));

		Course course = courseRepository.findById(courseID)
				.orElseThrow(() -> new CourseNotFoundException("Course not found"));

		Enrollment enrollment = new Enrollment();
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		enrollment.setCompletionStatus(CompletionStatus.NOT_COMPLETED);

		if (paymentStatus == PaymentStatus.COMPLETE)
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
			throw new EnrollmentNotFoundException("Enrollment Not Found:"+enrollmentId);
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
	public Boolean validatePasscode(String email, String passcode) {

		Student student = studentRepository.findByEmail(email)
				.orElseThrow(() -> new StudentNotFoundException("Student Not found for given email:" + email));

		if (student.getPassCode().equals(passcode)) {
			student.setVerified(true);
			studentRepository.save(student);
			return true;
		} else
			return false;
	}

	@Override
	public String generatePasscode(Integer length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder passcode = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			passcode.append(characters.charAt(random.nextInt(characters.length())));
		}

		return passcode.toString();
	}

	@Override
	public void savePasscode(String email, String passcode) {

		Student student = studentRepository.findByEmail(email)
				.orElseThrow(() -> new StudentNotFoundException("Student Not found for given email:" + email));

		student.setPassCode(passcode);
		studentRepository.save(student);

	}

	@Override
	public void updatePassword(String email, String newPassword) {
		Student student = studentRepository.findByEmail(email)
				.orElseThrow(() -> new StudentNotFoundException("Student Not found for given email:" + email));

		student.setPassword(passwordEncoder.encode(newPassword));
		studentRepository.save(student);
	}

	@Override
	@Transactional
	public void forgotPassword(String email) {
		
//		 	GenerateNewPassCode
//		    UpdateNewPassCode
//		    SendOutNewPassCodeToEmail WITH RESET LINK
		
		Student student = studentRepository.findByEmail(email)
				.orElseThrow(() -> new StudentNotFoundException("Student Not found for given email:" + email));
		
		String newPasscode = generatePasscode(Constants.PASSCODE_LENGTH);
		savePasscode(email,newPasscode);
		sendOutEmailWithPasscodeAndVerificationLink(student);
	}

	private void sendOutEmailWithPasscodeAndVerificationLink(Student student) {
		String passcode = Constants.PASSCODE + student.getPassCode();
		String forgotPasswordLink = Constants.FORGOT_PASSWORD_LINK;
		String emailBody =  Constants.PASSCODE + passcode + "\n" + forgotPasswordLink;
		
		emailSenderService.sendSimpleEmail(student.getEmail(), emailBody,
				Constants.EMAIL_RESET_SUBJECT);
	}

	@Override
	public void forgotPasswordSubmision(ForgotPasswordDTO forgotPasswordDTO) {
		
		if(!forgotPasswordDTO.getNewPassword().equals(forgotPasswordDTO.getConfirmNewPassword()))
		{
			throw new PasswordException("New Password and Confirmed New Password are not the same");
		}
		
		
		
	}


}

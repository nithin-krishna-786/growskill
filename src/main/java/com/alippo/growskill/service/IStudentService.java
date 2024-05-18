package com.alippo.growskill.service;

import java.util.List;

import com.alippo.growskill.dto.CreateStudentRequestDTO;
import com.alippo.growskill.dto.ForgotPasswordDTO;
import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.enums.PaymentStatus;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.exceptions.EnrollmentNotFoundException;
import com.alippo.growskill.entities.ClassInCourse;

public interface IStudentService {

	Student registerStudent(CreateStudentRequestDTO student);
	Enrollment enrollInCourse(Integer StudentID, Integer courseID, PaymentStatus paymentStatus);
	String attendClass(Enrollment enrollment, ClassInCourse classToAttend);
	Certificate downloadCertificate(Enrollment enrollment);
	List<Recording> downloadRecordings(Enrollment enrollment);
	Enrollment getEnrollmentById(Integer enrollmentId) throws EnrollmentNotFoundException;
	ClassInCourse getClassById(Integer classId,Integer enrollmentId) throws ClassInCourseNotFoundException;
	Boolean validatePasscode(String email,String passcode);
	String generatePasscode(Integer passcodeLength);
	void savePasscode(String email, String passcode);
	void updatePassword(String email, String newPassword);
	void forgotPassword(String email);
	void forgotPasswordSubmision(ForgotPasswordDTO forgotPasswordDTO);
}

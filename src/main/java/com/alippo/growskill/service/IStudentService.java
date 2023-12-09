package com.alippo.growskill.service;

import java.util.List;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.PaymentStatus;
import com.alippo.growskill.entities.Recording;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.exceptions.ClassInCourseNotFoundException;
import com.alippo.growskill.exceptions.EnrollmentNotFoundException;
import com.alippo.growskill.entities.ClassInCourse;

public interface IStudentService {

	Student registerStudent(Student student);
//	List<Enrollment> studentLogin(Student student);
	Enrollment enrollInCourse(Integer StudentID, Integer courseID, PaymentStatus paymentStatus);
	String attendClass(Enrollment enrollment, ClassInCourse classToAttend);
	Certificate downloadCertificate(Enrollment enrollment);
	List<Recording> downloadRecordings(Enrollment enrollment);
	Enrollment getEnrollmentById(Integer enrollmentId) throws EnrollmentNotFoundException;
	ClassInCourse getClassById(Integer classId,Integer enrollmentId) throws ClassInCourseNotFoundException;
	Student login(String email, String password);

}

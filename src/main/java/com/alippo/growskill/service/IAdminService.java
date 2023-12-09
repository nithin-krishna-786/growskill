package com.alippo.growskill.service;

import java.util.List;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

public interface IAdminService {

	Course createCourse(Specialization specialization,int numberOfClasses);
	ClassInCourse createClassInCourse(ClassInCourse classInCourse,Course course);
	Course assignInstructorToCourse(Integer instructorId, Integer courseID);
	Certificate createCertificate(Enrollment enrollment);
	
}

package com.alippo.growskill.service;

import com.alippo.growskill.entities.Certificate;
import com.alippo.growskill.entities.ClassInCourse;
import com.alippo.growskill.entities.Course;
import com.alippo.growskill.entities.Enrollment;
import com.alippo.growskill.enums.Specialization;

public interface IAdminService {
	Course createCourse(Specialization specialization,int numberOfClasses);
	ClassInCourse createClassInCourse(ClassInCourse classInCourse,Course course);
	Course assignInstructorToCourse(Integer instructorId, Integer courseID);
	Certificate createCertificate(Enrollment enrollment);
}

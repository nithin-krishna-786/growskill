package com.alippo.growskill.dto;

import java.util.List;

import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Specialization;

import lombok.Data;

@Data
public class CourseDTO {
    private int id;
    private Specialization specialization;
    private List<ClassInCourseDTO> classList;
    private InstructorDTO instructor;
    private Integer numberOfClasses;
}

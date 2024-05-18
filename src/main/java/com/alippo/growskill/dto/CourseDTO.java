package com.alippo.growskill.dto;

import java.util.List;

import com.alippo.growskill.enums.Specialization;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private Specialization specialization;
    private List<ClassInCourseDTO> classList;
    private InstructorDTO instructor;
    private Integer numberOfClasses;
}

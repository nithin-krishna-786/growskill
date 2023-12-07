package com.alippo.growskill.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClassInCourseDTO {
    private int id;
    private Integer courseId;
    private Date classDateAndTime;
    private String topic;
    private String zoomLink;
    private RecordingDTO recording;
}

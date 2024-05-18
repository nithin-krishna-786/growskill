package com.alippo.growskill.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class ClassInCourseDTO {
    private Long id;
    private Integer courseId;
    private LocalDateTime classDateAndTime;
    private String topic;
    private String zoomLink;
    private RecordingDTO recording;
}

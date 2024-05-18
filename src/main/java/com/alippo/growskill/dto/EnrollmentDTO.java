package com.alippo.growskill.dto;

import com.alippo.growskill.enums.CompletionStatus;
import com.alippo.growskill.enums.PaymentStatus;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private PaymentStatus paymentStatus;
    private CertificateDTO certificate;
    private Integer numberOfClassesCompleted;
    private CompletionStatus completionStatus;
    private Boolean eligibleToDownload;
}


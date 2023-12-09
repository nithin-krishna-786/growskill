package com.alippo.growskill.dto;

import com.alippo.growskill.entities.CompletionStatus;
import com.alippo.growskill.entities.PaymentStatus;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private int id;
    private int studentId;
    private int courseId;
    private PaymentStatus paymentStatus;
    private CertificateDTO certificate;
    private Integer numberOfClassesCompleted;
    private CompletionStatus completionStatus;
    private boolean eligibleToDownload;
}


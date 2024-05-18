package com.alippo.growskill.entities;

import com.alippo.growskill.enums.CompletionStatus;
import com.alippo.growskill.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enrollment")
@Data
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id",referencedColumnName = "id")
	private Student student;

	@OneToOne
	@JoinColumn(name = "course_id",referencedColumnName = "id")
	private Course course;

	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@OneToOne(cascade=CascadeType.ALL,mappedBy ="enrollment",orphanRemoval = true)
	private Certificate certificate;

	@Column(name = "number_of_classes_completed")
	private Integer numberOfClassesCompleted = 0;

	@Column(name = "completion_status")
	@Enumerated(EnumType.STRING)
	private CompletionStatus completionStatus;

	@Column(name = "eligible_to_download")
	private Boolean eligibleToDownload = false;
	
}

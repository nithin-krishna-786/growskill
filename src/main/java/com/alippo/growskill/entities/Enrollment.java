package com.alippo.growskill.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private int id;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@OneToOne
	@JoinColumn(name = "course_id",referencedColumnName = "course_id")
	private Course course;

	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@OneToOne(cascade=CascadeType.ALL,mappedBy ="enrollment")
	private Certificate certificate;

	@Column(name = "number_of_classes_completed")
	private Integer numberOfClassesCompleted;

	@Column(name = "completion_status")
	@Enumerated(EnumType.STRING)
	private CompletionStatus completionStatus;

	@Column(name = "boolean_eligible_to_download")
	private boolean eligibleToDownload = false;
	
	

}

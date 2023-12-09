package com.alippo.growskill.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "class")
@Data
public class ClassInCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id")
	private Course course;
	
	@Column(name = "class_date_and_time")
	private Date classDateAndTime;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "zoom_link")
	private String zoomLink;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recording_id",referencedColumnName = "recording_id")
	private Recording recording;
}

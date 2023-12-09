package com.alippo.growskill.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recording")
@Data
public class Recording {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recording_id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "class_id", referencedColumnName = "class_id")
	private ClassInCourse classRelatedToRecording;
	
	@Column(name="recording_link")
	private String recordingLink;

}

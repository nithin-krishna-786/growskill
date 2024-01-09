package com.alippo.growskill.entities;

import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int id;

	@Column(name = "specialization")
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "course")
	private List<ClassInCourse> classList = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id")
	private Instructor instructor;
	
	@Column(name ="number_of_classes")
	private Integer numberOfClasses;
	
	public void addClassInCourse(ClassInCourse classInCourse)
	{
		classList.add(classInCourse);
	}
}

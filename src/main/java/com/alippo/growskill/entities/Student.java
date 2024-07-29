package com.alippo.growskill.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student extends User {

	@OneToMany(mappedBy = "student")
	private List<Enrollment> enrollments;

	@Column(name = "college")
	private String college;

}

package com.alippo.growskill.entities;

import com.alippo.growskill.enums.Specialization;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "instructor")
@Data
public class Instructor extends User {

	@Column(name = "specialization")
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	private String qualification;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
}

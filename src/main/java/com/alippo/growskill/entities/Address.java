package com.alippo.growskill.entities;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String zipCode;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}

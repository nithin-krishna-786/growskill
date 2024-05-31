package com.alippo.growskill.dto;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
public class AddressDTO {
	private long id;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}

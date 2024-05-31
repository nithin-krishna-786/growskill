package com.alippo.growskill.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;

	private String name;

	private String username;

	private String password;

	private String email;

	private String phoneNumber;

	private Boolean verified;

	private String passCode;

	private AddressDTO address;

}

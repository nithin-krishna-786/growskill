package com.alippo.growskill.dto;

import lombok.Data;

@Data
public class LogInDTO {
	private String usernameOrEmail;
	private String password;
}

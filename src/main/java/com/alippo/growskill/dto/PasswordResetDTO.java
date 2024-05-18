package com.alippo.growskill.dto;

import lombok.Data;

@Data
public class PasswordResetDTO {
	
	private String email;
	private String oldPassword;
	private String newPassword;
	private String reenterNewPassword;


}

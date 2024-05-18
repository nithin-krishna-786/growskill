package com.alippo.growskill.dto;

import lombok.Data;

@Data
public class ForgotPasswordDTO {
	
	private String email;
	private String passcode;
	private String newPassword;
	private String confirmNewPassword;

}

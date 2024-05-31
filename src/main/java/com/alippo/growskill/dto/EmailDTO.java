package com.alippo.growskill.dto;


import lombok.Data;

@Data
public class EmailDTO {
	private String toEmail;
	private String body;
	private String subject;
}

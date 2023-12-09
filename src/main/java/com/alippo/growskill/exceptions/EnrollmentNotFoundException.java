package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnrollmentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 8846071284007170467L;

	public EnrollmentNotFoundException(String message)
	{
		super(message);
	}
	
}

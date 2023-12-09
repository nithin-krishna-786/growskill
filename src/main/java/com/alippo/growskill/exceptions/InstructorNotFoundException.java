package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InstructorNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224556967779231542L;

	public InstructorNotFoundException(String message)
	{
		super(message);
	}
	
}

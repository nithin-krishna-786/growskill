package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -988587452352512564L;

	public StudentNotFoundException(String message)
	{
		super(message);
	}
}
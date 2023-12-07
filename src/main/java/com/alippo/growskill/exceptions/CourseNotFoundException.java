package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -288591916921501519L;

	public CourseNotFoundException(String message)
	{
		super(message);
	}
	
}
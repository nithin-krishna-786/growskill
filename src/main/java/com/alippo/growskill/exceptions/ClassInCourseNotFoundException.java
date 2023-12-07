package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClassInCourseNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1926872113978455571L;

	public ClassInCourseNotFoundException(String message)
	{
		super(message);
	}
}

package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8905154184319865303L;

	
	public PasswordException(String message)
	{
		super(message);
	}
}

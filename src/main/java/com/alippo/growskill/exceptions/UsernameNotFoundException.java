package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8580754571169680968L;
	
	public UsernameNotFoundException(String message)
	{
		super(message);
	}

}

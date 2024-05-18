package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CredentialsException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5079736625177036316L;
	
	private HttpStatus status;
    private String message;
}

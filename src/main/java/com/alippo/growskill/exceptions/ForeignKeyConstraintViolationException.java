package com.alippo.growskill.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ForeignKeyConstraintViolationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6826502089335120589L;

	public ForeignKeyConstraintViolationException(String message) {
        super(message);
    }
}

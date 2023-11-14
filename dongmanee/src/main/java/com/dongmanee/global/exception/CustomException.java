package com.dongmanee.global.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}

	abstract public HttpStatus getHttpStatus();
}

package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class CustomJwtException extends JwtException {
	private final HttpStatus httpStatus;

	public CustomJwtException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public CustomJwtException(String message, HttpStatus httpStatus, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

}

package com.dongmanee.global.security.login.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class CustomAuthenticationException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public CustomAuthenticationException() {
		super("인증되지 않은 사용자입니다.");
	}

	public CustomAuthenticationException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

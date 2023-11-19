package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class CustomAccessDeniedException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

	public CustomAccessDeniedException() {
		super("권한이 없는 사용자입니다.");
	}

	public CustomAccessDeniedException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

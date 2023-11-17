package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class UnAuthorizedException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public UnAuthorizedException() {
		super("유저가 존재하지 않습니다.");
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class PasswordUnMatchException extends CustomException {

	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public PasswordUnMatchException() {
		super("비밀번호가 일치하지 않습니다.");
	}

	public PasswordUnMatchException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

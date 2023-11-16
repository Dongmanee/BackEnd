package com.dongmanee.global.security.login.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class UserNotExistException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public UserNotExistException() {
		super("유저가 존재하지 않습니다.");
	}

	public UserNotExistException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

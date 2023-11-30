package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class IllegalAccessException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public IllegalAccessException() {
		super("권한이 없습니다.");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

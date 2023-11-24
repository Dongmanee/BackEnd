package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class SnsNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public SnsNotFoundException() {
		super("Sns가 존재하지 않습니다");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

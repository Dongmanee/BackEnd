package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class IllegalTimeRangeException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public IllegalTimeRangeException() {
		super("종료시간은 시작시간 이후여야 합니다.");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

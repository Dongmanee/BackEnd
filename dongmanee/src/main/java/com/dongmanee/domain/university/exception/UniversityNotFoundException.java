package com.dongmanee.domain.university.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class UniversityNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public UniversityNotFoundException() {
		super("등록되지 않은 학교입니다.");
	}

	public UniversityNotFoundException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

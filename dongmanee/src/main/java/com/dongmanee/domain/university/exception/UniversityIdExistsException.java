package com.dongmanee.domain.university.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class UniversityIdExistsException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public UniversityIdExistsException() {
		super("등록되지 않은 학교입니다.");
	}

	public UniversityIdExistsException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

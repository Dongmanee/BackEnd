package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class DuplicateStudentIdException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public DuplicateStudentIdException() {
		super("이미 사용 중인 학번입니다.");
	}

	public DuplicateStudentIdException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
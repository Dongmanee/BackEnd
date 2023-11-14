package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class DuplicateEmailException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public DuplicateEmailException() {
		super("이미 사용 중인 이메일 입니다.");
	}

	public DuplicateEmailException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class DuplicateLoginIdException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public DuplicateLoginIdException() {
		super("이미 사용 중인 아이디 입니다.");
	}

	public DuplicateLoginIdException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
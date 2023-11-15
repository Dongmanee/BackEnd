package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class DuplicatePhoneException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public DuplicatePhoneException() {
		super("이미 사용 중인 전화번호 입니다.");
	}

	public DuplicatePhoneException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class UserNotFoundException extends CustomException {

	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public UserNotFoundException() {
		super("존재하지 않는 유저입니다.");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class DuplicatedClubSnsException extends CustomException {

	private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public DuplicatedClubSnsException() {
		super("중복된 Sns의 타입입니다.");
	}

	public DuplicatedClubSnsException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

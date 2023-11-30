package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class ClubUserNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ClubUserNotFoundException() {
		super("클럽에 존재하지 않는 사용자입니다.");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

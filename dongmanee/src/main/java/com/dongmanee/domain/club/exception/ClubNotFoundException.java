package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class ClubNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ClubNotFoundException() {
		super("클럽이 찾을 수 없습니다");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

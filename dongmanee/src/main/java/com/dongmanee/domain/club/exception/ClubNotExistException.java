package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class ClubNotExistException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ClubNotExistException() {
		super("클럽이 존재하지 않습니다");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class ClubScheduleNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ClubScheduleNotFoundException() {
		super("동아리 일정이 존재하지 않습니다");
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

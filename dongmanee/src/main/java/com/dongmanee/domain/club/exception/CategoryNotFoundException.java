package com.dongmanee.domain.club.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

public class CategoryNotFoundException extends CustomException {

	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public CategoryNotFoundException() {
		super("내부 오류로 카테고리가 잘못 설정되었습니다.");
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

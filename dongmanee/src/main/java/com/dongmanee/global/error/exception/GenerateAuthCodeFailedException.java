package com.dongmanee.global.error.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class GenerateAuthCodeFailedException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public GenerateAuthCodeFailedException() {
		super("인증 코드 생성 실패");
	}

	public GenerateAuthCodeFailedException(String message) {
		super(message);
	}
}

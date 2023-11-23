package com.dongmanee.domain.email.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

import lombok.Getter;

@Getter
public class EmailVerifiedException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public EmailVerifiedException() {
		super("이메일 인증 실패");
	}

	public EmailVerifiedException(String message) {
		super(message);
	}
}

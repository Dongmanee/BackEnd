package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

import lombok.Getter;

@Getter
public class OauthUserLocalLoginException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public OauthUserLocalLoginException() {
		super("Local유저가 아닙니다.");
	}

	public OauthUserLocalLoginException(String message) {
		super(message);
	}

}

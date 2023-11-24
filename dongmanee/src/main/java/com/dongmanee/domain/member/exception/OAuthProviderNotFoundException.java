package com.dongmanee.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

import lombok.Getter;

@Getter
public class OAuthProviderNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public OAuthProviderNotFoundException() {
		super("OAuth 제공자를 찾을 수 없습니다.");
	}

	public OAuthProviderNotFoundException(String message) {
		super(message);
	}
}

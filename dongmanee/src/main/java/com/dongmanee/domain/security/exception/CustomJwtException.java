package com.dongmanee.domain.security.exception;

import org.springframework.http.HttpStatus;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class CustomJwtException extends JwtException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public CustomJwtException() {
		super("인증에 실패하였습니다.");
	}
}

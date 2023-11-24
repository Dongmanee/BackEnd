package com.dongmanee.domain.email.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

import lombok.Getter;

@Getter
public class EmailSendingException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public EmailSendingException() {
		super("인증 메일 발송 실패");
	}

	public EmailSendingException(String message) {
		super(message);
	}

}

package com.dongmanee.domain.email.service;

public interface EmailService {
	void sendEmailVerificationCode(String toEmail);

	String verifyEmailAuthCode(String email, String authCode);

	void verifyFinalEmailAuthCode(String email, String emailAuthCode);
}

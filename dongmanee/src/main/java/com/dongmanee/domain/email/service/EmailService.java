package com.dongmanee.domain.email.service;

public interface EmailService {
	void sendEmailVerificationCode(String toEmail);

	void verifyEmailAuthCode(String email, String authCode);

	void checkEmailAuthentication(String email);
}

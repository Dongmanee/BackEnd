package com.dongmanee.domain.email.service;

public interface EmailService {
	void sendSingUpEmailAuthCode(String toEmail);

	String verifySignUpEmailAuthCode(String email, String authCode);
}

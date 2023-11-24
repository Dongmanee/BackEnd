package com.dongmanee.domain.email.service;

public interface EmailService {
	public void sendSingUpEmailAuthCode(String toEmail);

	String verifySignUpEmailAuthCode(String email, String authCode);
}

package com.dongmanee.domain.member.controller.port;

public interface SignUpControllerEmailService {
	public void sendSingUpEmailAuthCode(String toEmail);

	String verifySignUpEmailAuthCode(String email, String authCode);
}

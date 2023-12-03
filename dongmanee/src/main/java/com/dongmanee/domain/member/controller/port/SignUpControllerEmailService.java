package com.dongmanee.domain.member.controller.port;

public interface SignUpControllerEmailService {
	void sendSingUpEmailAuthCode(String toEmail);

	String verifySignUpEmailAuthCode(String email, String authCode);
}

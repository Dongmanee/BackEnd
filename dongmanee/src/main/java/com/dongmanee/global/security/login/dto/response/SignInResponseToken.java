package com.dongmanee.global.security.login.dto.response;

import lombok.Data;

@Data
public class SignInResponseToken {

	private String accessToken;

	private SignInResponseToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public static SignInResponseToken of(String accessToken) {
		return new SignInResponseToken(accessToken);
	}
}

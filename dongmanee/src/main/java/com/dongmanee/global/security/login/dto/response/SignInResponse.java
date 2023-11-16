package com.dongmanee.global.security.login.dto.response;

import lombok.Data;

@Data
public class SignInResponse {

	private String accessToken;

	private SignInResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public static SignInResponse of(String accessToken) {
		return new SignInResponse(accessToken);
	}
}

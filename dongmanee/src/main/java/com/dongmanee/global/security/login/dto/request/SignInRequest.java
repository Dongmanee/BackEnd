package com.dongmanee.global.security.login.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInRequest {
	private String loginId;
	private String password;
}

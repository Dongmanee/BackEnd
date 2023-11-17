package com.dongmanee.global.security.login.controller.port;

import com.dongmanee.global.security.login.dto.response.SignInResponseToken;

public interface SignInService {
	SignInResponseToken login(String loginId, String password);
}

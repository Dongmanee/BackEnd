package com.dongmanee.global.security.login.controller.port;

import com.dongmanee.global.security.login.dto.response.SignInResponse;

public interface SignInService {
	SignInResponse login(String loginId, String password);
}

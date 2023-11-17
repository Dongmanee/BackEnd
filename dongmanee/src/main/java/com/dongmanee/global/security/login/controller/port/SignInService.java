package com.dongmanee.global.security.login.controller.port;

import com.dongmanee.global.security.login.dto.response.JwsToken;

public interface SignInService {
	JwsToken login(String loginId, String password);
}

package com.dongmanee.domain.security.service;

import com.dongmanee.domain.security.dto.response.JwsToken;

public interface AuthService {
	JwsToken login(String loginId, String password);
}

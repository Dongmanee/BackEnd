package com.dongmanee.global.security.login.service;

import com.dongmanee.global.security.login.dto.response.JwsToken;

public interface SignInService {
    JwsToken login(String loginId, String password);
}

package com.dongmanee.domain.member.controller.port;

import com.dongmanee.domain.member.domain.Member;

public interface SignUpControllerSignUpService {
	void signup(String provider, Long externalProviderId, Member member, String emailAuthCode);
}

package com.dongmanee.domain.member.service;

import com.dongmanee.domain.member.domain.Member;

public interface SignUpService {
	void signup(String provider, Long externalProviderId, Member member);
}

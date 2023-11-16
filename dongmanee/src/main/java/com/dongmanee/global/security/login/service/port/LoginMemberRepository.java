package com.dongmanee.global.security.login.service.port;

import java.util.Optional;

import com.dongmanee.domain.member.domain.Member;

public interface LoginMemberRepository {
	Optional<Member> findByLoginId(String loginId);
}

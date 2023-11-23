package com.dongmanee.domain.member.service;

import com.dongmanee.domain.member.domain.Member;

public interface MemberService {
	void signup(Member member);

	Member getMemberFromUserId(Long id);
}

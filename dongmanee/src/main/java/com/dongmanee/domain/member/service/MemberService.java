package com.dongmanee.domain.member.service;

import com.dongmanee.domain.member.domain.Member;

public interface MemberService {
	Member getMemberFromUserId(Long memberId);
}

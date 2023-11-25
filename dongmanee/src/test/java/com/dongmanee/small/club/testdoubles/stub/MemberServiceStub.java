package com.dongmanee.small.club.testdoubles.stub;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.service.MemberService;

public class MemberServiceStub implements MemberService {
	@Override
	public Member getMemberFromUserId(Long memberId) {
		return Member.builder().build();
	}
}

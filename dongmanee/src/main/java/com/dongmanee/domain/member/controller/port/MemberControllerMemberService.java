package com.dongmanee.domain.member.controller.port;

import com.dongmanee.domain.member.domain.Member;

public interface MemberControllerMemberService {
	Member findById(Long id);
}

package com.dongmanee.domain.member.service;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestUpdateMemberDetails;

public interface MemberService {
	Member findById(Long id);

	Member updateMemberDetails(long id, RequestUpdateMemberDetails requestUpdateMemberDetails);
}

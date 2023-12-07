package com.dongmanee.domain.member.service;

import com.dongmanee.domain.member.domain.Member;

public interface MemberService {
	Member findById(Long id);

	Member updateMemberDetails(Member sourceMember);

	void updateMemberPassword(long id, String existingPassword, String newPassword);

	void updateMemberEmail(long id, String email, String code);
}

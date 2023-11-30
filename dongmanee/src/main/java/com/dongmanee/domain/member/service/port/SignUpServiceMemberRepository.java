package com.dongmanee.domain.member.service.port;

import java.util.Optional;

import com.dongmanee.domain.member.domain.Member;

public interface SignUpServiceMemberRepository {
	boolean existsByStudentId(String studentId);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Optional<Member> findByEmail(String email);

	Member save(Member member);
}

package com.dongmanee.domain.member.dao;

import java.util.Optional;

import com.dongmanee.domain.member.domain.Member;

public interface MemberRepository {
	boolean existsByStudentId(String studentId);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Optional<Member> findByEmail(String email);

	Optional<Member> findById(Long id);

	Member save(Member member);
}

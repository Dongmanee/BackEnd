package com.dongmanee.domain.member.dao;

import java.util.Optional;

import com.dongmanee.domain.member.dao.jpa.MemberJpaRepository;
import com.dongmanee.domain.member.domain.Member;

public interface MemberRepository extends MemberJpaRepository {
	boolean existsByStudentId(String studentId);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Optional<Member> findByEmail(String email);
}

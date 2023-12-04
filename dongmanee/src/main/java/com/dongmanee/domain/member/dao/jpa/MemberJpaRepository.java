package com.dongmanee.domain.member.dao.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.member.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
	boolean existsByStudentId(String studentId);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Optional<Member> findByEmail(String email);
}

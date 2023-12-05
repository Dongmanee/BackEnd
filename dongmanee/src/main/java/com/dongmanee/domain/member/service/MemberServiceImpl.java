package com.dongmanee.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.domain.email.utils.EmailRedisUtils;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestUpdateMemberDetails;
import com.dongmanee.domain.member.exception.MemberNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final EmailService emailService;
	private final MemberRepository memberJpaRepository;
	private final PasswordEncoder passwordEncoder;

	private final EmailRedisUtils emailRedis;

	@Override
	public Member findById(Long id) {
		return memberJpaRepository.findById(id).orElseThrow(MemberNotFoundException::new);
	}

	@Override
	public Member updateMemberDetails(long id, RequestUpdateMemberDetails request) {
		Member member = memberJpaRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		member.updateMember(
			request.getDepartment(),
			request.getPhone(),
			request.getBirth(),
			request.getProfileImageUrl()
		);

		return memberJpaRepository.save(member);
	}
}

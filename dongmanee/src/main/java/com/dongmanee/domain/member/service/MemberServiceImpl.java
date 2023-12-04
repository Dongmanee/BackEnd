package com.dongmanee.domain.member.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.exception.MemberNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	@Override
	public Member findById(Long id) {
		return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
	}
}

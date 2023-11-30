package com.dongmanee.domain.member.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.member.exception.DuplicateEmailException;
import com.dongmanee.domain.member.exception.DuplicateLoginIdException;
import com.dongmanee.domain.member.exception.DuplicatePhoneException;
import com.dongmanee.domain.member.exception.DuplicateStudentIdException;
import com.dongmanee.domain.member.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	@Override
	public Member getMemberFromUserId(Long id) {
		return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}
}

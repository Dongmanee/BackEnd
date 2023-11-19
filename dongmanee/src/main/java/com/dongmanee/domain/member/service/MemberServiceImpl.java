package com.dongmanee.domain.member.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.member.exception.DuplicateEmailException;
import com.dongmanee.domain.member.exception.DuplicateLoginIdException;
import com.dongmanee.domain.member.exception.DuplicatePhoneException;
import com.dongmanee.domain.member.exception.DuplicateStudentIdException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	@Override
	public void signup(Member member) {
		if (memberRepository.existsByLoginId(member.getLoginId())) {
			throw new DuplicateLoginIdException("이미 사용 중인 ID 입니다.");
		} else if (memberRepository.existsByStudentId(member.getStudentId())) {
			throw new DuplicateStudentIdException("이미 가입한 학번입니다.");
		} else if (memberRepository.existsByEmail(member.getEmail())) {
			throw new DuplicateEmailException("이미 사용 중인 이메일 입니다.");
		} else if (memberRepository.existsByPhone(member.getPhone())) {
			throw new DuplicatePhoneException("이미 사용 중인 전화번호 입니다.");
		}
		member.updateRole(Role.ROLE_USER);

		Member save = memberRepository.save(member);
	}
}

package com.dongmanee.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.exception.MemberNotFoundException;
import com.dongmanee.domain.security.exception.PasswordUnMatchException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Member findById(Long id) {
		return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
	}

	@Override
	public Member updateMemberDetails(Member sourceMember) {
		Member targetMember = memberRepository.findById(sourceMember.getId()).orElseThrow(MemberNotFoundException::new);

		targetMember.updateMember(
			sourceMember.getDepartment(),
			sourceMember.getPhone(),
			sourceMember.getBirth(),
			sourceMember.getProfileImageUrl()
		);

		return memberRepository.save(targetMember);
	}

	@Override
	public void updateMemberPassword(long id, String existingPassword, String newPassword) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		if (!passwordEncoder.matches(existingPassword, member.getPassword())) {
			throw new PasswordUnMatchException();
		}

		String encodedNewPassword = passwordEncoder.encode(newPassword);

		member.updatePassword(encodedNewPassword);

		memberRepository.save(member);
	}

	@Override
	public void updateMemberEmail(long id, String email) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		emailService.checkEmailAuthentication(email);

		member.updateEmail(email);

		memberRepository.save(member);
	}
}

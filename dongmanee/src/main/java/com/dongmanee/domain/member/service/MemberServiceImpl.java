package com.dongmanee.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestUpdateEmail;
import com.dongmanee.domain.member.dto.request.RequestUpdateMemberDetails;
import com.dongmanee.domain.member.dto.request.RequestUpdatePassword;
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
	public Member updateMemberDetails(long id, RequestUpdateMemberDetails request) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		member.updateMember(
			request.getDepartment(),
			request.getPhone(),
			request.getBirth(),
			request.getProfileImageUrl()
		);

		return memberRepository.save(member);
	}

	@Override
	public void updateMemberPassword(long id, RequestUpdatePassword request) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		if (!passwordEncoder.matches(request.getExistingPassword(), member.getPassword())) {
			throw new PasswordUnMatchException();
		}

		String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());

		member.updatePassword(encodedNewPassword);

		memberRepository.save(member);
	}

	@Override
	public void updateMemberEmail(long id, RequestUpdateEmail request) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		emailService.verifyFinalEmailAuthCode(request.getEmail(), request.getEmailAuthCode());

		member.updateEmail(request.getEmail());

		memberRepository.save(member);
	}
}

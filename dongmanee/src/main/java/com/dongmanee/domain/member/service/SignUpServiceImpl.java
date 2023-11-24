package com.dongmanee.domain.member.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.exception.EmailVerifiedException;
import com.dongmanee.domain.email.utils.EmailRedisUtils;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.member.exception.DuplicateEmailException;
import com.dongmanee.domain.member.exception.DuplicatePhoneException;
import com.dongmanee.domain.member.exception.DuplicateStudentIdException;
import com.dongmanee.domain.member.exception.OAuthProviderNotFoundException;
import com.dongmanee.domain.security.dao.AuthProviderRepository;
import com.dongmanee.domain.security.domain.AuthProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
	private final MemberRepository memberRepository;
	private final AuthProviderRepository authProviderRepository;
	private final EmailRedisUtils emailRedis;

	@Override
	public void signup(String provider, Long externalProviderId, Member member, String emailAuthCode) {
		if (emailRedis.getData(member.getEmail()).equals(emailAuthCode)) {
			emailRedis.deleteData(member.getEmail());
		} else {
			throw new EmailVerifiedException("이메일 인증을 해주세요.");
		}

		if (memberRepository.existsByStudentId(member.getStudentId())) {
			throw new DuplicateStudentIdException("이미 가입한 학번입니다.");
		}

		if (memberRepository.existsByEmail(member.getEmail())) {
			throw new DuplicateEmailException("이미 사용 중인 이메일 입니다.");
		}

		if (memberRepository.existsByPhone(member.getPhone())) {
			throw new DuplicatePhoneException("이미 사용 중인 전화번호 입니다.");
		}
		member.updateRole(Role.ROLE_USER);

		if (provider != null) {
			AuthProvider authProvider = authProviderRepository
				.findByAuthProviderAndExternalProviderId(provider, externalProviderId)
				.orElseThrow(() -> new OAuthProviderNotFoundException("OAuth 제공자를 찾을 수 없습니다."));

			authProvider.initializeMember(member);
		}

		Member save = memberRepository.save(member);
	}
}

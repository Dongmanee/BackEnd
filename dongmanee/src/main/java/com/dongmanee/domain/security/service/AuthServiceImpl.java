package com.dongmanee.domain.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.security.dto.response.JwsToken;
import com.dongmanee.domain.security.exception.PasswordUnMatchException;
import com.dongmanee.domain.security.exception.UnAuthorizedException;
import com.dongmanee.domain.security.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	public JwsToken login(String loginId, String password) {
		Member member = memberRepository.findByLoginId(loginId)
			.orElseThrow(UnAuthorizedException::new);

		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new PasswordUnMatchException();
		}

		String accessToken = jwtProvider.createToken(member.getId(), member.getRole().getKey());

		return JwsToken.of(accessToken);
	}
}

package com.dongmanee.global.security.login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.global.security.login.controller.port.SignInService;
import com.dongmanee.global.security.login.dto.request.SignInRequest;
import com.dongmanee.global.security.login.dto.response.SignInResponse;
import com.dongmanee.global.security.login.exception.PasswordUnMatchException;
import com.dongmanee.global.security.login.exception.UserNotExistException;
import com.dongmanee.global.security.login.provider.JwtProvider;
import com.dongmanee.global.security.login.service.port.LoginMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

	private final LoginMemberRepository loginMemberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	public SignInResponse login(SignInRequest request) {
		Member member = loginMemberRepository.findByLoginId(request.getLoginId())
			.orElseThrow(UserNotExistException::new);

		if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
			throw new PasswordUnMatchException();
		}

		String accessToken = jwtProvider.createToken(member.getLoginId(), member.getRole().getTitle());

		return SignInResponse.of(accessToken);
	}
}

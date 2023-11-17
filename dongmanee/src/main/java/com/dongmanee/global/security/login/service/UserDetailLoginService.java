package com.dongmanee.global.security.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.global.security.login.domain.MemberDetails;
import com.dongmanee.global.security.login.exception.UserNotExistException;
import com.dongmanee.global.security.login.service.port.LoginMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailLoginService implements UserDetailsService {
	private final LoginMemberRepository loginMemberRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Member member = loginMemberRepository.findByLoginId(userId)
			.orElseThrow(UserNotExistException::new);

		return MemberDetails.of(member);
	}
}

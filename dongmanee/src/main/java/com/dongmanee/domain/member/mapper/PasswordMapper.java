package com.dongmanee.domain.member.mapper;

import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Named("passwordEncoder")
public class PasswordMapper {
	private final PasswordEncoder passwordEncoder;

	@Named("passwordEncoded")
	public String passwordEncoded(String password) {
		return passwordEncoder.encode(password);
	}
}

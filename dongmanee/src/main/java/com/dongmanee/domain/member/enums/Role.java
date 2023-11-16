package com.dongmanee.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	ADMIN("ROLE_ADMIN", "관리자"),
	ROLE_USER("ROLE_USER", "일반 사용자");

	private final String key;
	private final String title;
}

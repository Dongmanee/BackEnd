package com.dongmanee.domain.club.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClubRole {
	HOST("ROLE_CLUB_HOST", "클럽 회장"),
	ADMIN("ROLE_CLUB_ADMIN","클럽 관리자"),
	USER("ROLE_CLUB_USER","클럽 맴버");

	private final String key;
	private final String title;
}

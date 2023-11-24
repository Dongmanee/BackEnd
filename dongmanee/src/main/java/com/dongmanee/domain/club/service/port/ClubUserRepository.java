package com.dongmanee.domain.club.service.port;

import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubUser;

public interface ClubUserRepository {
	ClubUser save(ClubUser clubUser);

	Optional<ClubUser> findClubUserWithMemberClub(Long memberId, Long clubId);
}

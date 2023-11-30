package com.dongmanee.domain.security.filter.port;

import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubUser;

public interface ClubAuthenticationFilterClubUserRepository{
	Optional<ClubUser> findClubUserWithMemberClub(Long memberId,Long clubId);
}

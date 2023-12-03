package com.dongmanee.domain.club.controller.port;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;

public interface ClubInfoUpdateService {
	Club editClubDescriptionAndAddress(Long memberId, Club club);

	ClubSns addClubSns(Long memberId, ClubSns clubSns, Long clubId);

	ClubSns editClubSns(ClubSns clubSns, Long clubId, Long snsId);

	void removeClubSns(Long clubId, Long snsId);
}

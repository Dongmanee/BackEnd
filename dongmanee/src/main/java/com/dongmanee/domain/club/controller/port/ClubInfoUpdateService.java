package com.dongmanee.domain.club.controller.port;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;

public interface ClubInfoUpdateService {
	void editClubDescriptionAndAddress(Long memberId, Club club);

	void addClubSns(Long memberId, ClubSns clubSns, Long clubId);

	void editClubSns(Long memberId, ClubSns clubSns, Long clubId, Long snsId);

	void removeClubSns(Long memberId, Long clubId, Long snsId);
}

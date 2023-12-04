package com.dongmanee.domain.club.service;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.member.domain.Member;

public interface ClubService {
	void createClub(Club club, Member member);

	Club editClubDescriptionAndAddress(Long memberId, Club club);

	ClubSns addClubSns(Long memberId, ClubSns clubSns, Long clubId);

	ClubSns editClubSns(ClubSns clubSns, Long clubId, Long snsId);

	void removeClubSns(Long clubId, Long snsId);

	ClubCategory findById(Long categoryId);
}

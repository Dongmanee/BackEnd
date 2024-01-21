package com.dongmanee.domain.club.service;

import java.util.List;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.member.domain.Member;

public interface ClubService {
	void createClub(Club club, Member member, List<ClubSns> clubSnsList);

	Club editClubDescriptionAndAddress(Long memberId, Club club);

	ClubSns upsertClubSns(Long memberId, ClubSns clubSns, Long clubId);

	List<Club> getJoinedClubList(Long memberId);

	void removeClubSns(Long clubId, Long snsId);

	ClubCategory findById(Long categoryId);
}

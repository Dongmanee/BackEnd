package com.dongmanee.domain.club.service;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.member.domain.Member;

public interface ClubInfoUpdateService {
	void editClubDescriptionAndAddress(Club club, Member member);

	void addClubSns(ClubSns clubSns, Long clubId, Member member);

	void editClubSns(ClubSns clubSns, Member member, Long clubId, Long snsId);

	void removeClubSns(Member member, Long clubId, Long snsId);
}

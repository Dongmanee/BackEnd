package com.dongmanee.small.club.testdoubles.stub;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.service.ClubInfoUpdateService;

public class ClubInfoUpdateServiceStub implements ClubInfoUpdateService {
	@Override
	public void editClubDescriptionAndAddress(Long memberId, Club club) {
		
	}

	@Override
	public void addClubSns(Long memberId, ClubSns clubSns, Long clubId) {

	}

	@Override
	public void editClubSns(Long memberId, ClubSns clubSns, Long clubId, Long snsId) {

	}

	@Override
	public void removeClubSns(Long memberId, Long clubId, Long snsId) {

	}
}

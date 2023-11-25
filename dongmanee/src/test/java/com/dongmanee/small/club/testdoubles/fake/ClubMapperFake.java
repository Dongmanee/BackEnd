package com.dongmanee.small.club.testdoubles.fake;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.mapper.ClubMapper;

public class ClubMapperFake implements ClubMapper {
	@Override
	public Club toEntity(RequestCreateClub requestCreateClub) {
		return null;
	}

	@Override
	public Club toEntity(Long id, RequestEditClubDescriptionAndAddress dto) {
		return null;
	}
}

package com.dongmanee.small.club.testdoubles.fake;

import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.dto.request.RequestSns;
import com.dongmanee.domain.club.mapper.ClubSnsMapper;

public class ClubSnsMapperFake implements ClubSnsMapper {
	@Override
	public ClubSns toEntity(RequestSns requestSns) {
		return null;
	}
}

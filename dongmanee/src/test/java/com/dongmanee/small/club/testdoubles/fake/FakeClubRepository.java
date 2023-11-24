package com.dongmanee.small.club.testdoubles.fake;

import java.util.ArrayList;
import java.util.List;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.service.port.ClubRepository;

public class FakeClubRepository implements ClubRepository {

	private final List<Club> list = new ArrayList<>();
	private Long id = 1L;

	@Override
	public Club save(Club club) {
		club.editClubId(id++);
		list.add(club);
		return club;
	}
}

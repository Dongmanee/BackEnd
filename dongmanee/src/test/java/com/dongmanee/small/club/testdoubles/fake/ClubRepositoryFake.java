package com.dongmanee.small.club.testdoubles.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.service.port.ClubRepository;

public class ClubRepositoryFake implements ClubRepository {

	private final List<Club> list = new ArrayList<>();
	private Long id = 1L;

	@Override
	public Club save(Club club) {
		Long targetId = club.getId();
		if (targetId != null) {
			list.removeIf(c -> c.getId().equals(club.getId()));
		}
		if (targetId == null) {
			club.editClubId(id++);
		}
		list.add(club);
		return club;
	}

	public Optional<Club> findByClubId(Long clubId) {
		return list.stream()
			.filter(c -> c.getId().equals(clubId))
			.findFirst();
	}

	public Optional<Club> getLastClub() {
		return findByClubId(id - 1);
	}
}

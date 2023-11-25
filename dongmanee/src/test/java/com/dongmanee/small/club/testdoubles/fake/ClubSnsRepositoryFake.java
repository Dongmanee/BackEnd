package com.dongmanee.small.club.testdoubles.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.service.port.ClubSnsRepository;

public class ClubSnsRepositoryFake implements ClubSnsRepository {
	private final List<ClubSns> list = new ArrayList<>();
	private long id = 1L;

	@Override
	public ClubSns save(ClubSns clubSns) {
		Long targetId = clubSns.getId();
		if (targetId != null) {
			list.removeIf(c -> c.getId().equals(clubSns.getId()));
		}
		if (targetId == null) {
			clubSns.editClubSnsId(id++);
		}
		list.add(clubSns);
		return clubSns;
	}

	@Override
	public Optional<ClubSns> findById(Long id) {
		return list.stream()
			.filter(clubSns -> clubSns.getId().equals(id))
			.findFirst();
	}

	@Override
	public void delete(ClubSns clubSns) {
		list.removeIf(item -> item.getId().equals(clubSns.getId()));
	}

	public Optional<ClubSns> getLastClubSns() {
		return findById(id - 1);
	}
}

package com.dongmanee.small.club.testdoubles.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.service.port.ClubUserRepository;

public class FakeClubUserRepository implements ClubUserRepository {
	private final List<ClubUser> list = new ArrayList<>();
	private long id = 1L;

	@Override
	public ClubUser save(ClubUser clubUser) {
		clubUser.editClubUserId(id++);
		list.add(clubUser);
		return clubUser;
	}

	@Override
	public Optional<ClubUser> findClubUserWithMemberClub(Long memberId, Long clubId) {
		return list.stream()
			.filter(clubUser -> clubUser.getClub().getId().equals(clubId))
			.findFirst();
	}
}

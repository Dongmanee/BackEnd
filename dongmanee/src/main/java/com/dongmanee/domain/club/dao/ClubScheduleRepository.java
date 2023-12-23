package com.dongmanee.domain.club.dao;

import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubSchedule;

public interface ClubScheduleRepository {
	ClubSchedule save(ClubSchedule newClubSchedule);

	Optional<ClubSchedule> findByIdAndClubId(long id, long clubId);
}

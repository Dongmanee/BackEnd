package com.dongmanee.domain.club.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubSchedule;

public interface ClubScheduleRepository {
	ClubSchedule save(ClubSchedule newClubSchedule);

	Optional<ClubSchedule> findByIdAndClubId(long id, long clubId);

	List<ClubSchedule> findByClubIdAndStartTimeBetween(long clubId, LocalDateTime startOfMonth,
		LocalDateTime endOfMonth);
}

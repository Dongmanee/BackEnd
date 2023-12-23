package com.dongmanee.domain.club.service;

import java.time.LocalDate;
import java.util.List;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;

public interface ClubScheduleService {
	void createSchedule(ClubSchedule newClubSchedule);

	void updateSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule newClubSchedule);

	List<ClubSchedule> findMonthlyScheduleByClubId(long clubId, LocalDate date);

	ClubSchedule findClubSchedule(long clubId, long clubScheduleId);
}

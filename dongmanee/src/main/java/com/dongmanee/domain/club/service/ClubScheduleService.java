package com.dongmanee.domain.club.service;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;

public interface ClubScheduleService {
	void createSchedule(ClubSchedule newClubSchedule);

	void updateSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule newClubSchedule);
}

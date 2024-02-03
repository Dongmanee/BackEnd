package com.dongmanee.domain.club.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;

public interface ClubScheduleService {
	void createClueSchedule(ClubSchedule newClubSchedule);

	void updateClueSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule newClubSchedule);

	ClubSchedule findClubSchedule(long clubId, long clubScheduleId);

	void deleteClubSchedule(long clubId, long scheduleId);

	Slice<ClubSchedule> findAllClubScheduleBySearchCriteriaBeforeCursor(long clubId, LocalDateTime cursor,
		RequestClubScheduleSearchCriteria searchCriteria, Pageable pageable);
}

package com.dongmanee.domain.club.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;

public interface ClubScheduleService {
	void createSchedule(ClubSchedule newClubSchedule);

	void updateSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule newClubSchedule);

	List<ClubSchedule> findMonthlyScheduleByClubId(long clubId, LocalDate date);

	ClubSchedule findClubSchedule(long clubId, long clubScheduleId);

	void deleteSchedule(long clubId, long scheduleId);

	Slice<ClubSchedule> findAllBySearchCriteriaBeforeCursor(long clubId, LocalDateTime cursor,
		RequestClubScheduleSearchCriteria searchCriteria, Pageable pageable);
}

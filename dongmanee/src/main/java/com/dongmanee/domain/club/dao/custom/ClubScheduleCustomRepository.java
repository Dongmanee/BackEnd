package com.dongmanee.domain.club.dao.custom;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;

public interface ClubScheduleCustomRepository {
	Slice<ClubSchedule> findAllBySearchCriteriaBeforeCursor(long clubId, LocalDateTime cursor,
		RequestClubScheduleSearchCriteria searchCriteria, Pageable pageable);
}

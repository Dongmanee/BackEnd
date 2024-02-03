package com.dongmanee.domain.club.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.dao.ClubScheduleJpaRepository;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.exception.ClubScheduleNotFoundException;
import com.dongmanee.domain.club.exception.IllegalTimeRangeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubScheduleServiceImpl implements ClubScheduleService {
	private final ClubScheduleJpaRepository clubScheduleRepository;

	@Override
	public void createClueSchedule(ClubSchedule newClubSchedule) {
		if (!newClubSchedule.isAllDay() && newClubSchedule.getStartTime().isAfter(newClubSchedule.getEndTime())) {
			throw new IllegalTimeRangeException();
		}
		clubScheduleRepository.save(newClubSchedule);
	}

	@Override
	public void updateClueSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule request) {
		ClubSchedule clubSchedule = clubScheduleRepository.findByIdAndClubId(clubScheduleId, clubId)
			.orElseThrow(ClubScheduleNotFoundException::new);

		clubSchedule.update(request);

		clubScheduleRepository.save(clubSchedule);
	}

	@Override
	public ClubSchedule findClubSchedule(long clubId, long clubScheduleId) {
		return clubScheduleRepository.findByIdAndClubId(clubScheduleId, clubId)
			.orElseThrow(ClubScheduleNotFoundException::new);
	}

	@Override
	public void deleteClubSchedule(long clubId, long scheduleId) {
		clubScheduleRepository.deleteByIdAndClubId(scheduleId, clubId);
	}

	@Override
	public Slice<ClubSchedule> findAllClubScheduleBySearchCriteriaBeforeCursor(long clubId, LocalDateTime cursor,
		RequestClubScheduleSearchCriteria searchCriteria, Pageable pageable) {
		return clubScheduleRepository.findAllBySearchCriteriaBeforeCursor(clubId, cursor, searchCriteria, pageable);
	}
}

package com.dongmanee.domain.club.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.dao.ClubScheduleRepository;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.exception.IllegalTimeRangeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubScheduleServiceImpl implements ClubScheduleService {
	private final ClubScheduleRepository clubScheduleRepository;

	@Override
	public void createSchedule(ClubSchedule newClubSchedule) {
		if (!newClubSchedule.isAllDay() && newClubSchedule.getStartTime().isAfter(newClubSchedule.getEndTime())) {
			throw new IllegalTimeRangeException();
		}
		clubScheduleRepository.save(newClubSchedule);
	}
}

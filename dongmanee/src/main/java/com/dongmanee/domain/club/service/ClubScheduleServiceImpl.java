package com.dongmanee.domain.club.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.dao.ClubScheduleRepository;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.exception.ClubScheduleNotFoundException;
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

	@Override
	public void updateSchedule(long clubId, long clubScheduleId, RequestUpdateClubSchedule request) {
		ClubSchedule clubSchedule = clubScheduleRepository.findByIdAndClubId(clubScheduleId, clubId)
			.orElseThrow(ClubScheduleNotFoundException::new);

		clubSchedule.update(request);

		clubScheduleRepository.save(clubSchedule);
	}

	@Override
	public List<ClubSchedule> findMonthlyScheduleByClubId(long clubId, LocalDate date) {
		LocalDateTime startOfMonth = date.withDayOfMonth(1).atStartOfDay();
		LocalDateTime endOfMonth = date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
		return clubScheduleRepository.findByClubIdAndStartTimeBetween(clubId, startOfMonth, endOfMonth);
	}

	@Override
	public ClubSchedule findClubSchedule(long clubId, long clubScheduleId) {
		return clubScheduleRepository.findByIdAndClubId(clubScheduleId, clubId)
			.orElseThrow(ClubScheduleNotFoundException::new);
	}

	@Override
	public void deleteSchedule(long clubId, long scheduleId) {
		clubScheduleRepository.deleteByIdAndClubId(scheduleId, clubId);
	}
}

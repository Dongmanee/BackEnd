package com.dongmanee.domain.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.mapper.ClubScheduleMapper;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.service.ClubScheduleService;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.global.utils.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clubs/{club-id}/schedules")
@RequiredArgsConstructor
public class ClubScheduleController {
	private final ClubScheduleService clubScheduleService;
	private final ClubService clubService;
	private final ClubScheduleMapper clubScheduleMapper;

	@PostMapping("")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') or hasAnyAuthority('ROLE_ADMIN')")
	public ApiResult<?> createSchedule(@PathVariable("club-id") long clubId,
		@RequestBody RequestCreateClubSchedule request) {
		ClubSchedule newClubSchedule = clubScheduleMapper.toEntity(clubId, request, clubService);
		clubScheduleService.createSchedule(newClubSchedule);
		return ApiResult.isCreated("동아리 일정 생성 성공");
	}
}

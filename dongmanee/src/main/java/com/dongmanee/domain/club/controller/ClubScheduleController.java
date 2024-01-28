package com.dongmanee.domain.club.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.apidoc.ClubScheduleApiDocs;
import com.dongmanee.domain.club.controller.mapper.ClubScheduleMapper;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.dto.response.ResponseClubSchedule;
import com.dongmanee.domain.club.service.ClubScheduleService;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.global.utils.ApiResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clubs/{club-id}/schedules")
@RequiredArgsConstructor
public class ClubScheduleController implements ClubScheduleApiDocs {
	private final ClubScheduleService clubScheduleService;
	private final ClubService clubService;
	private final ClubScheduleMapper clubScheduleMapper;

	@GetMapping("")
	public ApiResult<Slice<ClubSchedule>> findAllClubScheduleBySearchCriteriaBeforeCursor(
		@PathVariable("club-id") long clubId,
		@RequestParam(value = "cursor", required = false) LocalDateTime cursor,
		@ModelAttribute RequestClubScheduleSearchCriteria searchCriteria,
		@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		Slice<ClubSchedule> clubScheduleSlice = clubScheduleService.findAllBySearchCriteriaBeforeCursor(clubId, cursor,
			searchCriteria,
			pageable);
		return ApiResult.isOk(clubScheduleSlice, "SUCCESS");
	}

	@PostMapping("")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') or hasAnyAuthority('ROLE_ADMIN')")
	public ApiResult<?> createSchedule(@PathVariable("club-id") long clubId,
		@Valid @RequestBody RequestCreateClubSchedule request) {
		ClubSchedule newClubSchedule = clubScheduleMapper.toEntity(clubId, request, clubService);
		clubScheduleService.createSchedule(newClubSchedule);
		return ApiResult.isCreated("동아리 일정 생성 성공");
	}

	@GetMapping("/{schedule-id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN', 'ROLE_CLUB_USER') or hasAnyAuthority('ROLE_ADMIN')")
	public ApiResult<ResponseClubSchedule> findClubSchedule(@PathVariable("club-id") long clubId,
		@PathVariable("schedule-id") long clubScheduleId) {
		ClubSchedule clubSchedule = clubScheduleService.findClubSchedule(clubId, clubScheduleId);
		ResponseClubSchedule responseClubSchedule = clubScheduleMapper.toResponseClubSchedule(clubSchedule);
		return ApiResult.isOk(responseClubSchedule, "동아리 일정 조회 성공");
	}

	@PatchMapping("/{schedule-id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') or hasAnyAuthority('ROLE_ADMIN')")
	public ApiResult<?> updateSchedule(@PathVariable("club-id") long clubId,
		@PathVariable("schedule-id") long clubScheduleId,
		@Valid @RequestBody RequestUpdateClubSchedule request) {
		clubScheduleService.updateSchedule(clubId, clubScheduleId, request);
		return ApiResult.isNoContent("동아리 일정 수정 성공");
	}

	@DeleteMapping("/{schedule-id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') or hasAnyAuthority('ROLE_ADMIN')")
	public ApiResult<?> deleteSchedule(@PathVariable("club-id") long clubId,
		@PathVariable("schedule-id") long scheduleId) {
		clubScheduleService.deleteSchedule(clubId, scheduleId);
		return ApiResult.isNoContent("동아리 일정 삭제 성공");
	}
}

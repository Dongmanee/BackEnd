package com.dongmanee.domain.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.apidoc.ClubInfoEditControllerApiDocs;
import com.dongmanee.domain.club.controller.mapper.ClubMapper;
import com.dongmanee.domain.club.controller.mapper.ClubResponseMapper;
import com.dongmanee.domain.club.controller.mapper.ClubSnsMapper;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.dto.request.RequestSns;
import com.dongmanee.domain.club.dto.response.ClubEditResponse;
import com.dongmanee.domain.club.dto.response.ClubSnsResponseDto;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "클럽 정보 수정", description = "클럽 정보수정 API 명세서")
@RestController
@RequiredArgsConstructor
public class ClubInfoEditeController implements ClubInfoEditControllerApiDocs {
	private final ClubMapper clubMapper;
	private final ClubService clubInfoUpdateService;
	private final ClubSnsMapper clubSnsMapper;
	private final ClubResponseMapper clubResponseMapper;

	@PatchMapping("/clubs/{club-id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ApiResult<?> editClubDescriptionAndAddress(@RequestBody RequestEditClubDescriptionAndAddress dto,
		@AuthenticationPrincipal UserDetails userDetails, @PathVariable("club-id") Long clubId) {
		Club club = clubMapper.toEntity(clubId, dto);

		Club editClub = clubInfoUpdateService
			.editClubDescriptionAndAddress(Long.parseLong(userDetails.getUsername()), club);
		ClubEditResponse responseDto = clubResponseMapper.toDto(editClub);
		return ApiResult.isOk(responseDto, "클럽 정보가 수정되었습니다.");
	}

	@PutMapping("/clubs/{club-id}/sns")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ApiResult<?> upsertClubSns(@Valid @RequestBody RequestSns request,
		@AuthenticationPrincipal UserDetails userDetails, @PathVariable("club-id") Long clubId) {
		ClubSns requestSns = clubSnsMapper.toEntity(request);

		ClubSns createdClubSns = clubInfoUpdateService
			.upsertClubSns(Long.parseLong(userDetails.getUsername()), requestSns, clubId);
		ClubSnsResponseDto responseDto = clubResponseMapper.toDto(createdClubSns);
		return ApiResult.isCreated(responseDto, "클럽 Sns가 추가/수정 되었습니다");
	}

	@DeleteMapping("/clubs/{club-id}/sns/{sns-id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CLUB_HOST', 'ROLE_CLUB_ADMIN') and hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ApiResult<?> removeClubSns(@PathVariable("club-id") Long clubId, @PathVariable("sns-id") Long snsId) {

		clubInfoUpdateService.removeClubSns(clubId, snsId);
		return ApiResult.isNoContent("클럽 Sns가 삭제되었습니다");
	}

	// TODO 1. 지원서 기능 추가 이후 지원서 수정 기능 추가
}

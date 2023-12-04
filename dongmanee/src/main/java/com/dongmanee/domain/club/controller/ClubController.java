package com.dongmanee.domain.club.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.apidoc.ClubControllerApiDocs;
import com.dongmanee.domain.club.controller.mapper.ClubMapper;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.service.ClubCategoryService;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.service.MemberService;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "클럽", description = "클럽 API 명세서")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ClubController implements ClubControllerApiDocs {
	private final ClubMapper clubMapper;
	private final ClubService clubService;
	private final MemberService memberService;
	private final ClubCategoryService clubCategoryService;

	//TODO: 클럽 정보 가져오는 URL 리턴으로 변경
	@PostMapping("/clubs")
	public ApiResult<?> createClub(@Valid @RequestBody RequestCreateClub createClub,
		@AuthenticationPrincipal UserDetails userDetails) {
		Member requestMember = memberService.findById(Long.parseLong(userDetails.getUsername()));
		Club club = clubMapper.toEntity(createClub, clubCategoryService);
		clubService.createClub(club, requestMember);
		return ApiResult.isCreated("클럽이 생성되었습니다.");
	}

	// TODO 1. 클럽 가입 요청 기능 추가
}

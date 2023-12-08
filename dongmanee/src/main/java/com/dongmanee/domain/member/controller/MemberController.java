package com.dongmanee.domain.member.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.mapper.ClubMapper;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.response.MemberJoinedClubResponseDto;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.domain.member.controller.apidoc.MemberControllerApiDocs;
import com.dongmanee.domain.member.controller.mapper.MemberMapper;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.response.ResponseMember;
import com.dongmanee.domain.member.dto.response.ResponseMemberDetails;
import com.dongmanee.domain.member.service.MemberService;
import com.dongmanee.domain.security.domain.CustomUserDetails;
import com.dongmanee.global.utils.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController implements MemberControllerApiDocs {
	private final MemberService memberService;
	private final MemberMapper memberMapper;
	private final ClubService clubService;
	private final ClubMapper clubMapper;

	@GetMapping("/{member-id}")
	public ApiResult<ResponseMember> findMemberById(@PathVariable("member-id") Long id) {
		Member member = memberService.findById(id);
		ResponseMember response = memberMapper.toResponseMember(member);
		return ApiResult.isOk(response, "멤버 정보 조회 성공");
	}

	@GetMapping("/details")
	public ApiResult<ResponseMemberDetails> findMemberDetailsById(@AuthenticationPrincipal UserDetails userDetails) {
		Member member = memberService.findById(Long.parseLong(userDetails.getUsername()));
		ResponseMemberDetails response = memberMapper.toResponseMemberDetails(member);
		return ApiResult.isOk(response, "멤버 세부 정보 조회 성공");
	}

	@GetMapping("/members/clubs")
	public ApiResult<List<MemberJoinedClubResponseDto>> clubJoinLists(
		@AuthenticationPrincipal CustomUserDetails userDetails) {
		List<Club> joinedClubList = clubService.getJoinedClubList(Long.parseLong(userDetails.getUsername()));
		List<MemberJoinedClubResponseDto> responseDto = clubMapper.toMemberJoinedClubResponseDto(joinedClubList);
		return ApiResult.isOk(responseDto, "조회에 성공하였습니다.");
	}
}

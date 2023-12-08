package com.dongmanee.domain.member.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.club.controller.mapper.ClubMapper;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.response.MemberJoinedClubResponseDto;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.domain.member.controller.apidoc.MemberControllerApiDocs;
import com.dongmanee.domain.member.controller.mapper.MemberMapper;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestUpdateEmail;
import com.dongmanee.domain.member.dto.request.RequestUpdateMemberDetails;
import com.dongmanee.domain.member.dto.request.RequestUpdatePassword;
import com.dongmanee.domain.member.dto.response.ResponseMember;
import com.dongmanee.domain.member.dto.response.ResponseMemberDetails;
import com.dongmanee.domain.member.service.MemberService;
import com.dongmanee.domain.security.domain.CustomUserDetails;
import com.dongmanee.global.utils.ApiResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController implements MemberControllerApiDocs {
	private final MemberService memberService;
	private final MemberMapper memberMapper;
	private final EmailService emailService;
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

	@PatchMapping("/details")
	public ApiResult<ResponseMemberDetails> updateMemberDetails(@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody RequestUpdateMemberDetails request) {
		Member sourceMember = memberMapper.toEntity(Long.parseLong(userDetails.getUsername()), request);
		Member updatedMember = memberService.updateMemberDetails(sourceMember);
		ResponseMemberDetails response = memberMapper.toResponseMemberDetails(updatedMember);
		return ApiResult.isOk(response, "멤버 세부 정보 수정 성공");
	}

	@PatchMapping("/password")
	public ApiResult<?> updateMemberPassword(@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody RequestUpdatePassword request) {
		memberService.updateMemberPassword(Long.parseLong(userDetails.getUsername()), request.getExistingPassword(),
			request.getNewPassword());

		return ApiResult.isNoContent("비밀번호 변경 성공");
	}

	@PostMapping("/emails/verification-code")
	public ApiResult<?> sendEmailAuthCode(@Valid @RequestBody RequestEmailAuthCode requestEmailAuthCode) {
		emailService.sendEmailVerificationCode(requestEmailAuthCode.getEmail());

		return ApiResult.isNoContent("인증 코드 발송");
	}

	@PostMapping("/emails/confirm")
	public ApiResult<?> verifyEmailAuthCode(@Valid @RequestBody RequestVerifyAuthCode requestVerifyAuthCode) {
		emailService.verifyEmailAuthCode(requestVerifyAuthCode.getEmail(), requestVerifyAuthCode.getCode());

		return ApiResult.isNoContent("인증 성공");
	}

	@PatchMapping("/emails")
	public ApiResult<?> updateMemberEmail(@AuthenticationPrincipal UserDetails userDetails,
		@Valid @RequestBody RequestUpdateEmail request) {
		memberService.updateMemberEmail(Long.parseLong(userDetails.getUsername()), request.getEmail());

		return ApiResult.isNoContent("이메일 변경 성공");
	}

	@GetMapping("/members/clubs")
	public ApiResult<List<MemberJoinedClubResponseDto>> clubJoinLists(
		@AuthenticationPrincipal CustomUserDetails userDetails) {
		List<Club> joinedClubList = clubService.getJoinedClubList(Long.parseLong(userDetails.getUsername()));
		List<MemberJoinedClubResponseDto> responseDto = clubMapper.toMemberJoinedClubResponseDto(joinedClubList);
		return ApiResult.isOk(responseDto, "조회에 성공하였습니다.");
	}
}

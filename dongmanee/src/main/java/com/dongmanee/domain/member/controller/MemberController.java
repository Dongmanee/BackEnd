package com.dongmanee.domain.member.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.dongmanee.domain.member.mapper.MemberMapper;
import com.dongmanee.domain.member.service.MemberService;
import com.dongmanee.global.utils.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final MemberMapper memberMapper;

	@PostMapping("/signup")
	public ApiResponse UserSignUp(@Valid @RequestBody RequestSignup request) throws URISyntaxException {
		Member newMember = memberMapper.toEntity(request);
		memberService.signup(newMember);
		return ApiResponse.success("회원가입 성공");
	}
}

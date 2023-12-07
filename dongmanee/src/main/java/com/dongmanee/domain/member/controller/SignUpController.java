package com.dongmanee.domain.member.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.domain.member.controller.apidoc.SignUpControllerApiDocs;
import com.dongmanee.domain.member.controller.mapper.MemberMapper;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.dongmanee.domain.member.service.SignUpService;
import com.dongmanee.domain.university.service.UniversityService;
import com.dongmanee.global.utils.ApiResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController implements SignUpControllerApiDocs {
	private final SignUpService signUpService;
	private final UniversityService universityService;
	private final EmailService emailService;

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@PostMapping()
	public ApiResult<?> userSignUp(@Valid @RequestBody RequestSignup request) {
		Member newMember = memberMapper.toEntity(request, universityService, passwordEncoder);
		signUpService.signup(request.getProvider(), request.getExternalProviderId(), newMember);
		return ApiResult.isNoContent("회원가입 성공");
	}

	@PostMapping("/emails/verification-code")
	public ApiResult<?> sendSignUpEmailAuthCode(@Valid @RequestBody RequestEmailAuthCode requestEmailAuthCode) {
		emailService.sendEmailVerificationCode(requestEmailAuthCode.getEmail());

		return ApiResult.isNoContent("인증 코드 발송");
	}

	@PostMapping("/emails/confirm")
	public ApiResult<?> verifySignUpEmailAuthCode(@Valid @RequestBody RequestVerifyAuthCode requestVerifyAuthCode) {
		emailService.verifyEmailAuthCode(requestVerifyAuthCode.getEmail(), requestVerifyAuthCode.getCode());

		return ApiResult.isNoContent("인증 성공");
	}
}

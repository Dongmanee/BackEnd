package com.dongmanee.domain.member.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.email.dto.response.ResponseEmailAuthCode;
import com.dongmanee.domain.member.controller.mapper.MemberMapper;
import com.dongmanee.domain.member.controller.port.SignUpControllerEmailService;
import com.dongmanee.domain.member.controller.port.SignUpControllerSignUpService;
import com.dongmanee.domain.member.controller.port.SingUpControllerUniversityService;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.dongmanee.global.utils.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
	private final SignUpControllerSignUpService signUpService;
	private final SingUpControllerUniversityService universityService;
	private final SignUpControllerEmailService emailService;

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@PostMapping()
	public ApiResponse<?> userSignUp(@Valid @RequestBody RequestSignup request) {
		Member newMember = memberMapper.toEntity(request, universityService, passwordEncoder);
		signUpService.signup(request.getProvider(), request.getExternalProviderId(), newMember,
			request.getEmailAuthCode());
		return ApiResponse.isOk("회원가입 성공");
	}

	@PostMapping("/code/send")
	public ApiResponse<?> sendSignUpEmailAuthCode(@RequestBody RequestEmailAuthCode requestEmailAuthCode) {
		emailService.sendSingUpEmailAuthCode(requestEmailAuthCode.getEmail());

		return ApiResponse.isOk("인증 코드 발송");
	}

	@PostMapping("/code/check")
	public ApiResponse<?> verifySignUpEmailAuthCode(@RequestBody RequestVerifyAuthCode requestVerifyAuthCode) {
		String code = emailService.verifySignUpEmailAuthCode(requestVerifyAuthCode.getEmail(),
			requestVerifyAuthCode.getCode());

		return ApiResponse.isOk(new ResponseEmailAuthCode(code), "인증 성공");
	}
}

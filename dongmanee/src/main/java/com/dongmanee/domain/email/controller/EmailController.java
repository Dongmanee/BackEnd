package com.dongmanee.domain.email.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.email.dto.response.ResponseEmailAuthCode;
import com.dongmanee.domain.email.service.EmailService;
import com.dongmanee.global.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
	private final EmailService emailService;

	@PostMapping("/code/send")
	public ApiResponse<?> sendSignUpEmailAuthCode(@RequestBody RequestEmailAuthCode requestEmailAuthCode) {
		emailService.sendSingUpEmailAuthCode(requestEmailAuthCode.getEmail());

		return ApiResponse.success("인증 코드 발송");
	}

	@PostMapping("/code/check")
	public ApiResponse<?> verifySignUpEmailAuthCode(@RequestBody RequestVerifyAuthCode requestVerifyAuthCode) {
		String code = emailService.verifySignUpEmailAuthCode(requestVerifyAuthCode.getEmail(),
			requestVerifyAuthCode.getCode());

		return ApiResponse.success(new ResponseEmailAuthCode(code), "인증 성공");
	}
}
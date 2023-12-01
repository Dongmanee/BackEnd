package com.dongmanee.domain.member.controller.apidoc;

import org.springframework.web.bind.annotation.RequestBody;

import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public interface SignUpControllerApiDocs {
	@Operation(summary = "요청 성공")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "회원가입 성공",
					value = """
						{
							"status":204,
							"message":"회원가입 성공",
							"data": null
						}
							"""))),
	})
	ApiResult<?> userSignUp(@Valid @RequestBody RequestSignup request);

	@Operation(summary = "이메일 인증 코드 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 코드 전송 성공",
					value = """
						{
							"status":204,
							"message":"인증 코드 전송 성공",
							"data": null
						}
							"""))),
		@ApiResponse(responseCode = "400",
			description = "요청 에러",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = {
					@ExampleObject(name = "인증 메일 발송 실패",
						value = """
							{
							    "status":400,
							    "message":"인증 메일 발송 실패",
							    "data": null
							}
								""")
				}))
	})
	ApiResult<?> sendSignUpEmailAuthCode(@RequestBody RequestEmailAuthCode requestEmailAuthCode);

	@Operation(summary = "이메일 인증 코드 검증")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 성공",
					value = """
						{
							"status":204,
							"message":"이메일 인증 성공",
							"data": null
						}
							"""))),
		@ApiResponse(responseCode = "401",
			description = "요청 실패",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = {
					@ExampleObject(name = "인증 실패",
						value = """
							{
								"status":401,
								"message":"이메일 인증 실패",
								"data": null
							}
								""")
				}))
	})
	ApiResult<?> verifySignUpEmailAuthCode(@RequestBody RequestVerifyAuthCode requestVerifyAuthCode);
}

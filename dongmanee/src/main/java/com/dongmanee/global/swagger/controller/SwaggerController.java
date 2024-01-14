package com.dongmanee.global.swagger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "로그인", description = "로그인 API 명세")
public class SwaggerController {
	@PostMapping("/login")
	@Operation(summary = "로그인", description = "사용자 로그인을 위한 엔드포인트",
		requestBody = @RequestBody(
			required = true,
			content = @Content(schema = @Schema(implementation = LoginRequest.class),
				examples = @ExampleObject(name = "로그인 요청", value = "{\"email\": \"eamil@domain.com\", \"password\": \"passwd123!\"}"))))
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "로그인 요청 성공",
					value = """
						{
							"status":200,
							"message":"로그인 성공",
							"data": {
								"accessToken": "bearerToken"
							}
						}
							"""))),
		@ApiResponse(responseCode = "401",
			description = "인증 실패",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "로그인 인증 실패",
					value = """
						{
							"timestamp":"2024-01-14T13:54:55.620+00:00",
							"status":401,
							"error":"Unauthorized",
							"path":"/login"
						}
							"""))),
	})
	public void fakeLogin() {
		throw new IllegalStateException("이 메소드는 실행되지 않습니다. Swagger 문서화를 위한 것입니다.");
	}
}

class LoginRequest {
	private String email;
	private String password;
}
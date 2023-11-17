package com.dongmanee.global.security.login.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInRequest {
	@NotBlank(message = "로그인 id를 입력해 주세요")
	private String loginId;
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;
}

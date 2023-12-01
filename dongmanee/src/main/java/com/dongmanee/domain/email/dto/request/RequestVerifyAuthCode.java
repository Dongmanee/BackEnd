package com.dongmanee.domain.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestVerifyAuthCode {
	@Schema(description = "사용자의 이메일",
		example = "example@example.org")
	@NotBlank
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@Schema(description = "이메일 인증 코드",
		example = "123456")
	@NotNull
	@Size(min = 6, max = 6, message = "옳바르지 않은 인증코드 입니다.")
	private String code;
}

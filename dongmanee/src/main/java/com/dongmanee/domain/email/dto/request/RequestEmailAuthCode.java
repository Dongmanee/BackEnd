package com.dongmanee.domain.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmailAuthCode {
	@Schema(description = "사용자의 이메일",
		example = "example@example.org")
	@NotBlank
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
}

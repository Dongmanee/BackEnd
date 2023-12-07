package com.dongmanee.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateEmail {
	@Schema(description = "사용자의 이메일 주소",
		example = "example@example.org")
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@Schema(description = "이메일인증 완료 후 사용자가 받은 인증 코드",
		example = "123456")
	@NotBlank(message = "이메일 인증을 해주세요.")
	@Size(min = 6, max = 6, message = "옳바르지 않은 인증코드 입니다.")
	private String code;
}

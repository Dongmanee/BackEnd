package com.dongmanee.domain.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
	@NotBlank(message = "로그인 id를 입력해 주세요")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "아이디는 6~12자 영문 대 소문자, 숫자만 사용 가능합니다.")
	private String loginId;

	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;
}

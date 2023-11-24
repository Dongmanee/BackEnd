package com.dongmanee.domain.member.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSignup {
	private String provider;

	private Long externalProviderId;

	@NotNull(message = "대학교를 입력해주세요.")
	private Long universityId;

	@NotBlank(message = "학번을 입력해주세요.")
	@Size(min = 8, max = 10, message = "학번은 8~10자 사이여야 합니다.")
	private String studentId;

	@NotBlank(message = "학과를 선택해주세요.")
	private String department;

	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phone;

	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@NotBlank(message = "이메일 인증을 해주세요.")
	private String emailAuthCode;

	@NotNull(message = "생년월일을 입력해주세요.")
	@Past(message = "생년월일은 현재 날짜보다 이전이어야 합니다.")
	private LocalDate birth;

	@NotBlank(message = "이름을 입력해주세요")
	@Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
	private String name;

	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	private String profileImageUrl;
}

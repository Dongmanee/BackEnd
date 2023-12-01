package com.dongmanee.domain.member.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "대학교의 고유 식별자. 사용자가 속한 대학교를 나타냅니다.",
		example = "1")
	@NotNull(message = "대학교를 입력해주세요.")
	private Long universityId;

	@Schema(description = "사용자의 학번 (8~10자 사이의 문자열)",
		example = "19991919")
	@NotBlank(message = "학번을 입력해주세요.")
	@Size(min = 8, max = 10, message = "학번은 8~10자 사이여야 합니다.")
	private String studentId;

	@Schema(description = "사용자가 속한 학과의 이름",
		example = "컴퓨터공학과")
	@NotBlank(message = "학과를 선택해주세요.")
	private String department;

	@Schema(description = "사용자의 전화번호",
		example = "010-1234-5678")
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phone;

	@Schema(description = "사용자의 이메일 주소",
		example = "example@example.org")
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@Schema(description = "이메일인증 완료 후 사용자가 받은 인증 코드",
		example = "123456")
	@NotBlank(message = "이메일 인증을 해주세요.")
	private String emailAuthCode;

	@Schema(description = "사용자의 생년월일",
		example = "1999-01-01")
	@NotNull(message = "생년월일을 입력해주세요.")
	@Past(message = "생년월일은 현재 날짜보다 이전이어야 합니다.")
	private LocalDate birth;

	@Schema(description = "사용자의 이름",
		example = "홍길동")
	@NotBlank(message = "이름을 입력해주세요")
	@Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
	private String name;

	@Schema(description = "사용자의 비밀번호 (사용자가 OAuth 로그인을 통해 회원가입 하는 경우 전달하지 않습니다)",
		example = "@@Test1234")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	@Schema(description = "OAuth 제공자 명 (사용자가 로컬 회원가입 하는 경우에는 이 값을 전달하지 않습니다)",
		example = "kakao",
		allowableValues = {"kakao"})
	private String provider;

	@Schema(description = "OAuth 제공자가 제공해주는 정보의 고유 식별자 (사용자가 로컬 회원가입 하는 경우에는 이 값을 전달하지 않습니다)")
	private Long externalProviderId;
}

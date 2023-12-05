package com.dongmanee.domain.member.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
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
public class RequestUpdateMemberDetails {
	@Schema(description = "사용자가 속한 학과의 이름",
		example = "컴퓨터공학과")
	private String department;

	@Schema(description = "사용자의 전화번호",
		example = "010-1234-5678")
	private String phone;

	@Schema(description = "사용자의 생년월일",
		example = "1999-01-01")
	@Past(message = "생년월일은 현재 날짜보다 이전이어야 합니다.")
	private LocalDate birth;

	@Schema(description = "사용자의 프로필 이미지 url")
	private String profileImageUrl;
}

package com.dongmanee.domain.member.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.dongmanee.domain.member.domain.Member}
 */
@Value
public class ResponseMemberDetails implements Serializable {
	@Schema(description = "사용자의 학교")
	@NotNull
	String university;

	@Schema(description = "사용자의 학번")
	@NotNull
	String studentId;

	@Schema(description = "사용자의 학과")
	@NotNull
	String department;

	@Schema(description = "사용자의 이름")
	@NotNull
	String name;

	@Schema(description = "사용자의 전화번호")
	@NotNull
	String phone;

	@Schema(description = "사용자의 이메일")
	@NotNull
	String email;

	@Schema(description = "사용자의 생일")
	@NotNull
	LocalDate birth;

	@Schema(description = "사용자의 프로필 이미지 Url")
	String profileImageUrl;
}
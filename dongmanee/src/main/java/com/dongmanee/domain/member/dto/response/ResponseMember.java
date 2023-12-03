package com.dongmanee.domain.member.dto.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.dongmanee.domain.member.domain.Member}
 */
@Value
public class ResponseMember implements Serializable {
	@Schema(description = "사용자의 학교")
	@NotNull()
	String university;

	@Schema(description = "사용자의 학과")
	@NotNull
	String department;

	@Schema(description = "사용자의 이름")
	@NotNull
	String name;

	@Schema(description = "사용자의 프로필 이미지 Url")
	String profileImageUrl;
}
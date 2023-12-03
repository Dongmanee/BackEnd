package com.dongmanee.domain.member.dto.response;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.dongmanee.domain.member.domain.Member}
 */
@Value
public class ResponseMember implements Serializable {
	@NotNull
	String university;

	@NotNull
	String department;

	@NotNull
	String name;

	String profileImageUrl;
}
package com.dongmanee.domain.member.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.dongmanee.domain.member.domain.Member}
 */
@Value
public class ResponseMemberDetails implements Serializable {
	@NotNull
	String university;

	@NotNull
	String studentId;

	@NotNull
	String department;

	@NotNull
	String name;

	@NotNull
	String phone;

	@NotNull
	String email;

	@NotNull
	LocalDate birth;

	String profileImageUrl;
}
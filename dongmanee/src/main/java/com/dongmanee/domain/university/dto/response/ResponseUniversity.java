package com.dongmanee.domain.university.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ResponseUniversity {
	@Schema(description = "대학교의 식별자", example = "1")
	long id;

	@Schema(description = "대학교 명", example = "한국대학교")
	String name;
}
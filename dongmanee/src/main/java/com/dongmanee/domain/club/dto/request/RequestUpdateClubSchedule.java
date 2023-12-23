package com.dongmanee.domain.club.dto.request;

import java.time.LocalDateTime;

import org.openapitools.jackson.nullable.JsonNullable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateClubSchedule {
	@Schema(description = "일정 시작 시간", example = "2023-10-11T14:12:12")
	private LocalDateTime startTime;

	@Schema(description = "일정 종료 시간", example = "2023-10-11T15:12:12")
	private LocalDateTime endTime;

	@Schema(description = "종일 일정인지 여부", example = "false")
	private Boolean isAllDay;

	@Schema(description = "일정 제목", example = "노가리")
	@Nullable
	@NotBlank
	private String title;

	@Schema(description = "일정 장소", example = "동아리방")
	private JsonNullable<String> location;

	@Schema(description = "일정 설명", example = "모여서 수다 떨기")
	private JsonNullable<String> description;

	@Schema(description = "일정 비용", example = "1000")
	private JsonNullable<Integer> cost;

	@Schema(description = "참여자 수", example = "10")
	private JsonNullable<Integer> numberParticipants;
}

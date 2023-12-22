package com.dongmanee.domain.club.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateClubSchedule {
	@NotNull(message = "일정 시작 시간을 입력해주세요")
	@Schema(description = "일정 시작 시간", example = "2023-10-11T14:12:12")
	private LocalDateTime startTime;

	@Schema(description = "일정 종료 시간", example = "2023-10-11T15:12:12")
	private LocalDateTime endTime;

	@NotNull
	@Schema(description = "종일 일정인지 여부", example = "false")
	private boolean isAllDay;

	@Schema(description = "일정 장소", example = "동아리방")
	private String location;

	@NotEmpty(message = "일정 제목을 입력해주세요.")
	@Schema(description = "일정 제목", example = "노가리")
	private String title;

	@Schema(description = "일정 설명", example = "모여서 수다 떨기")
	private String description;

	@Schema(description = "일정 비용", example = "1000")
	private int cost;

	@Schema(description = "참여자 수", example = "10")
	private int numberParticipants;
}

package com.dongmanee.domain.club.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestClubScheduleSearchCriteria {
	@Schema(description = "일정 시작 날짜 검색 조건", example = "2024-01-12")
	LocalDate startDate;

	@Schema(description = "일정 종료 날짜 검색 조건", example = "2024-01-13")
	LocalDate endDate;
}

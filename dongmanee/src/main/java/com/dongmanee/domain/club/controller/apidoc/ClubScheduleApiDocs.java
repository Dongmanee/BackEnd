package com.dongmanee.domain.club.controller.apidoc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "동아리 일정", description = "동아리 일정 API 명세서")
public interface ClubScheduleApiDocs {
	@Operation(summary = "동아리 일정 생성 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "201",
			description = "일정 생성 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 일정 생성 성공",
					value = """
						{
							"status": 201,
							"message": "동아리 일정 생성 성공",
							"data": null
						}
												""")
			)),
	})
	ApiResult<?> createSchedule(@PathVariable("club-id") long clubId, @RequestBody RequestCreateClubSchedule request);

	@Operation(summary = "동아리 일정 수정 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "일정 수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 일정 수정 성공",
					value = """
						{
							"status": 204,
							"message": "동아리 일정 수정 성공",
							"data": null
						}
												""")
			)),
	})
	ApiResult<?> updateSchedule(@PathVariable("club-id") long clubId, @PathVariable("schedule-id") long clubScheduleId,
		@RequestBody RequestUpdateClubSchedule request);
}

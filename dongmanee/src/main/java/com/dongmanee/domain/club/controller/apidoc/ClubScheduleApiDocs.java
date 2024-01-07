package com.dongmanee.domain.club.controller.apidoc;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.dto.response.ResponseClubSchedule;
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

	@Operation(summary = "동아리 월간 일정 조회 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "월간 일정 조회 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 월간 일정 조회 성공",
					value = """
						{
							"status": 200,
							"message": "동아리 월간 일정 조회 성공",
							"data": [
								{
									"id": 1,
									"startTime": "2023-10-11T14:12:12",
									"endTime": "2023-10-11T15:12:12",
									"location": "test",
									"title": "test",
									"description": "test",
									"cost": 1000,
									"numberParticipants": 100,
									"allDay": false
								},
								{
									"id": 2,
									"startTime": "2023-10-15T14:12:12",
									"endTime": "2023-10-17T15:12:12",
									"location": "test2",
									"title": "test2",
									"description": "test2",
									"cost": 1000,
									"numberParticipants": 100,
									"allDay": false
								}
						  	]
						}
												""")
			)),
	})
	ApiResult<List<ResponseClubSchedule>> findMonthlyScheduleByClubId(@PathVariable("club-id") long clubId,
		@RequestParam("date") LocalDate date);

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

	@Operation(summary = "동아리 월간 단건 조회 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "월간 일정 조회 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 일정 조회 성공",
					value = """
						{
							"status": 200,
							"message": "동아리 일정 조회 성공",
							"data": {
								"id": 1,
								"startTime": "2023-10-11T14:12:12",
								"endTime": "2023-10-11T15:12:12",
								"location": "test",
								"title": "test",
								"description": "test",
								"cost": 1000,
								"numberParticipants": 100,
								"allDay": false
							}
						}
												""")
			)),
	})
	ApiResult<ResponseClubSchedule> findClubSchedule(@PathVariable("club-id") long clubId,
		@PathVariable("schedule-id") long clubScheduleId);

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

	@Operation(summary = "동아리 일정 삭제 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "일정 삭제 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 일정 삭제 성공",
					value = """
						{
							"status": 204,
							"message": "동아리 일정 삭제 성공",
							"data": null
						}
												""")
			)),
	})
	public ApiResult<?> deleteSchedule(@PathVariable("club-id") long clubId,
		@PathVariable("schedule-id") long scheduleId);
}

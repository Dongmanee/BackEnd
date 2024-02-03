package com.dongmanee.domain.club.controller.apidoc;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
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

	@Operation(summary = "동아리 일정 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "일정 조회 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "동아리 알정 조회 성공",
					value = """
						{
						     "status": 200,
						     "message": "SUCCESS",
						     "data": {
						         "content": [
						             {
						                 "createdAt": "2024-01-28T21:05:09.425966",
						                 "updatedAt": "2024-01-28T21:05:09.425966",
						                 "id": 3,
						                 "startTime": "2023-10-12T14:12:12",
						                 "endTime": "2023-10-12T15:12:12",
						                 "location": "test",
						                 "title": "test",
						                 "description": "test",
						                 "cost": 1000,
						                 "numberParticipants": 100,
						                 "allDay": false
						             },
						             {
						                 "createdAt": "2024-01-28T21:05:08.971421",
						                 "updatedAt": "2024-01-28T21:05:08.971421",
						                 "id": 2,
						                 "startTime": "2023-10-12T14:12:12",
						                 "endTime": "2023-10-12T15:12:12",
						                 "location": "test",
						                 "title": "test",
						                 "description": "test",
						                 "cost": 1000,
						                 "numberParticipants": 100,
						                 "allDay": false
						             },
						             {
						                 "createdAt": "2024-01-28T21:05:08.509113",
						                 "updatedAt": "2024-01-28T21:05:08.509113",
						                 "id": 1,
						                 "startTime": "2023-10-12T14:12:12",
						                 "endTime": "2023-10-12T15:12:12",
						                 "location": "test",
						                 "title": "test",
						                 "description": "test",
						                 "cost": 1000,
						                 "numberParticipants": 100,
						                 "allDay": false
						             }
						         ],
						         "pageable": {
						             "pageNumber": 0,
						             "pageSize": 3,
						             "sort": {
						                 "empty": true,
						                 "unsorted": true,
						                 "sorted": false
						             },
						             "offset": 0,
						             "paged": true,
						             "unpaged": false
						         },
						         "first": true,
						         "last": true,
						         "size": 3,
						         "number": 0,
						         "sort": {
						             "empty": true,
						             "unsorted": true,
						             "sorted": false
						         },
						         "numberOfElements": 3,
						         "empty": false
						     }
						 }
												""")
			)),
	})
	ApiResult<Slice<ClubSchedule>> findAllClubScheduleBySearchCriteriaBeforeCursor(
		@Schema(description = "동아리 일정을 조회할 동아리 ID")
		@PathVariable("club-id") long clubId,
		@Schema(description = "동아리 일정 조회 커서/ 시작 시간이 커서 이전인 일정 조회")
		@RequestParam(value = "cursor", required = false) LocalDateTime cursor,
		@Schema(description = "동아리 일정 검색 조건/ 검색 조건에 부합하는 일정 조회")
		@ModelAttribute RequestClubScheduleSearchCriteria searchCriteria,
		@Schema(description = "동아리 일정 페이지 네이션 조건", examples = """
					{
						 "size": 3,
					}
			""")
		@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable);

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

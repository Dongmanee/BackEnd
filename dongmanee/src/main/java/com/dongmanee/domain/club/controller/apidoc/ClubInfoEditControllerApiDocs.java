package com.dongmanee.domain.club.controller.apidoc;

import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.dto.request.RequestSns;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ClubInfoEditControllerApiDocs {
	@Operation(summary = "클럽 정보 수정 - 클럽 설명, 클럽 주소")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 수정 성공",
					value = """
						{
							"status": 200,
							"message": "클럽 정보가 수정되었습니다.",
							"data": null
						}
												""")
			)),
		@ApiResponse(responseCode = "401",
			description = "인증에 실패하였습니다. - JWT토큰 혹은 ClubUserRole",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 실패",
					value = """
						{
							"status": 401,
							"message": "인증에 실패하였습니다.",
							"daa": null
						}
												""")
			)),
		@ApiResponse(responseCode = "404",
			description = "클럽에 존재하지 않는 유저",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "유저 조회 실패",
					value = """
						{
							"status": 404,
							"message": "클럽에 존재하지 않는 사용자입니다."
							"data": null
						}
												""")
			))
	})
	ApiResult<?> editClubDescriptionAndAddress(RequestEditClubDescriptionAndAddress dto, UserDetails userDetails,
		@Parameter(description = "클럽의 고유 식별 번호") Long clubId);

	@Operation(summary = "클럽 정보 수정 - 클럽 Sns 추가/수정")
	@ApiResponses({
		@ApiResponse(responseCode = "201",
			description = "추가/수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 추가/수정 성공",
					value = """
						{
							"status": 201,
							"message": "클럽 Sns가 추가/수정 되었습니다",
							"data": {
								"title":"editTitle",
								"url":"editUrl"
							}
						}
												""")
			)),
		@ApiResponse(responseCode = "400",
			description = """
					1. 잘못된 타입 전송 - 사전에 합의 되지 않은 타입의 SNS타입 전송, 응답 방식은 추후에 변경 예정
					2. 잘못된 URL 전송 - URL 형식에 맞지 않는 주소 전송, 응답 방식은 추후에 변경 예정
				""",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "데이터 검증 실패",
					value = """
						1: {
							"timestamp": "2024-01-21T15:05:02.906+00:00",
							"status": 400,
							"error":"Bad Request",
							"path": "/clubs/1/sns"
						},
						2: {
							"status": 400,
							"error":"must be a valid URL",
							"data": "null"
						}
												""")
			)),
		@ApiResponse(responseCode = "401",
			description = "인증에 실패하였습니다. - JWT토큰 혹은 ClubUserRole",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 실패",
					value = """
						{
							"status": 401,
							"message": "인증에 실패하였습니다.",
							"daa": null
						}
												""")
			))
	})
	ApiResult<?> upsertClubSns(RequestSns request, UserDetails userDetails,
		@Parameter(description = "클럽의 고유 식별 번호") Long clubId);

	@Operation(summary = "클럽 정보 수정 - 클럽 Sns 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 삭제 성공",
					value = """
						{
							"status": 204,
							"message": "클럽 Sns가 삭제되었습니다",
							"data": null
						}
												""")
			)),
		@ApiResponse(responseCode = "401",
			description = "인증에 실패하였습니다. - JWT토큰 혹은 ClubUserRole",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 실패",
					value = """
						{
							"status": 401,
							"message": "인증에 실패하였습니다.",
							"daa": null
						}
												""")
			)),
		@ApiResponse(responseCode = "404",
			description = "클럽에 존재하지 않는 Sns",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 Sns 조회 실패",
					value = """
						{
							"status": 404,
							"message": "Sns가 존재하지 않습니다"
							"data": null
						}
												""")
			))
	})
	ApiResult<?> removeClubSns(@Parameter(description = "클럽의 고유 식별 번호") Long clubId,
		@Parameter(description = "클럽Sns 고유 식별 번호") Long snsId);
}

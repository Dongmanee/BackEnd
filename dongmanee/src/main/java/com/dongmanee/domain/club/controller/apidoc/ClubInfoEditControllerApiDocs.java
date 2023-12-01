package com.dongmanee.domain.club.controller.apidoc;

import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.dto.request.RequestSns;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
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
							"status": 201,
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
							"path": "/club/1",
							"timestamp": "2023-12-02T01:51:10.278370"
						}
												""")
			)),
		@ApiResponse(responseCode = "400",
			description = "클럽에 존재하지 않는 유저",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "유저 조회 실패",
					value = """
						{
							"status": 400,
							"message": "클럽에 존재하지 않는 사용자입니다."
							"data": null
						}
												""")
			))
	})
	ApiResult<?> editClubDescriptionAndAddress(RequestEditClubDescriptionAndAddress dto,
		UserDetails userDetails, Long clubId);

	@Operation(summary = "클럽 정보 수정 - 클럽 Sns 추가")
	@ApiResponses({
		@ApiResponse(responseCode = "201",
			description = "수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 수정 성공",
					value = """
						{
							"status": 201,
							"message": "클럽 Sns가 추가되었습니다",
							"data": {
								"title":"editTitle",
								"url":"editUrl"
							}
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
							"path": "/club/1",
							"timestamp": "2023-12-02T01:51:10.278370"
						}
												""")
			))
	})
	ApiResult<?> addClubSns(RequestSns request, UserDetails userDetails, Long clubId);

	@Operation(summary = "클럽 정보 수정 - 클럽 Sns 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 수정 성공",
					value = """
						{
							"status": 200,
							"message": "클럽 Sns가 수정되었습니다",
							"data": {
								"title":"editTitle",
								"url":"editUrl"
							}
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
							"path": "/club/1",
							"timestamp": "2023-12-02T01:51:10.278370"
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
	ApiResult<?> editClubSns(RequestSns request, Long clubId, Long snsId);

	@Operation(summary = "클럽 정보 수정 - 클럽 Sns 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "수정 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 정보 삭제 성공",
					value = """
						{
							"status": 200,
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
							"path": "/club/1",
							"timestamp": "2023-12-02T01:51:10.278370"
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
	ApiResult<?> removeClubSns(Long clubId, Long snsId);
}

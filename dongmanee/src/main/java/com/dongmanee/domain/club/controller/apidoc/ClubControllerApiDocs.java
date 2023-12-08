package com.dongmanee.domain.club.controller.apidoc;

import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.security.domain.CustomUserDetails;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ClubControllerApiDocs {
	@Operation(summary = "클럽 생성 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "201",
			description = "생성 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽 생성 성공",
					value = """
						{
							"status": 201,
							"message": "클럽이 생성되었습니다.",
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
			description = "카테고리 검색 실패",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "카테고리 검색 실패",
					value = """
						{
							"status": 404,
							"message": "내부 오류로 카테고리가 잘못 설정되었습니다.",
							"data": null
						}
												""")
			))
	})
	ApiResult<?> createClub(RequestCreateClub createClub, CustomUserDetails userDetails);

}

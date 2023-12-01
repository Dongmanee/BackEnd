package com.dongmanee.domain.university.controller.apidoc;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.dongmanee.domain.university.dto.response.ResponseUniversity;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UniversityControllerApiDocs {
	@Operation(summary = "대학교 목록 반환")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "대학교 목록 조회 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "대학교 목록 조회 성공",
					value = """
							{
								 "status": 200,
								 "message": "대학교 목록 조회 성공",
								 "data": [
									 {
										 "id": 1,
										 "name": "한국대학교"
									 },
									 {
										 "id": 2,
										 "name": "대한대학교"
									 }
								 ]
							 }
						""")))
	})
	@GetMapping("/universities")
	ApiResult<List<ResponseUniversity>> getAllUniversityList();
}

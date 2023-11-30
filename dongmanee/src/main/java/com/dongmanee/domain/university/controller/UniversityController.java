package com.dongmanee.domain.university.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.university.controller.mapper.UniversityMapper;
import com.dongmanee.domain.university.controller.port.UniversityControllerUniversityService;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "대학교", description = "대학교 API 명세")
@RestController
@RequiredArgsConstructor
public class UniversityController {
	private final UniversityControllerUniversityService universityControllerUniversityService;
	private final UniversityMapper universityMapper;

	@Operation(summary = "대학교 목록 반환",
		responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "대학교 목록 조회 성공",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ApiResult.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ApiResult.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ApiResult.class)))
		})
	@GetMapping("/universities")
	public ApiResult<List<ResponseUniversity>> getAllUniversityList() {
		List<University> universityList = universityControllerUniversityService.findAll();
		List<ResponseUniversity> responseUniversityList = universityList.stream()
			.map(universityMapper::toResponseUniversity)
			.toList();
		return ApiResult.isOk(responseUniversityList, "대학교 목록 조회 성공");
	}
}

package com.dongmanee.domain.university.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.university.controller.apidoc.UniversityControllerApiDocs;
import com.dongmanee.domain.university.controller.mapper.UniversityMapper;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;
import com.dongmanee.domain.university.service.UniversityService;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "대학교", description = "대학교 API 명세")
@RestController
@RequiredArgsConstructor
public class UniversityController implements UniversityControllerApiDocs {
	private final UniversityService universityService;
	private final UniversityMapper universityMapper;

	@GetMapping("/universities")
	public ApiResult<List<ResponseUniversity>> getAllUniversityList() {
		List<University> universityList = universityService.findAll();
		List<ResponseUniversity> responseUniversityList = universityList.stream()
			.map(universityMapper::toResponseUniversity)
			.toList();
		return ApiResult.isOk(responseUniversityList, "대학교 목록 조회 성공");
	}
}

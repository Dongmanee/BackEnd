package com.dongmanee.domain.university.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.university.controller.mapper.UniversityMapper;
import com.dongmanee.domain.university.controller.port.UniversityControllerUniversityService;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;
import com.dongmanee.global.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UniversityController {
	private final UniversityControllerUniversityService universityControllerUniversityService;
	private final UniversityMapper universityMapper;

	@GetMapping("/universities")
	public ApiResponse<?> getAllUniversityList() {
		List<University> universityList = universityControllerUniversityService.findAll();
		List<ResponseUniversity> responseUniversityList = universityList.stream()
			.map(universityMapper::toResponseUniversity)
			.toList();
		return ApiResponse.isOk(responseUniversityList, "대학교 목록 조회 성공");
	}
}

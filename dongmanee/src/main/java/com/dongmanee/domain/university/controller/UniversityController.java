package com.dongmanee.domain.university.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;
import com.dongmanee.domain.university.mapper.UniversityMapper;
import com.dongmanee.domain.university.service.UniversityService;
import com.dongmanee.global.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UniversityController {
	private final UniversityService universityService;
	private final UniversityMapper universityMapper;

	@GetMapping("/universities")
	public ApiResponse<?> getAllUniversityList() {
		List<University> universityList = universityService.findAll();
		List<ResponseUniversity> responseUniversityList = universityList.stream()
			.map(universityMapper::toResponseUniversity)
			.toList();
		return ApiResponse.success(responseUniversityList, "대학교 목록 조회 성공");
	}
}

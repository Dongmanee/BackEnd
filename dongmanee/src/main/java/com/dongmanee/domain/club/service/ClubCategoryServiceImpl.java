package com.dongmanee.domain.club.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.controller.port.ClubControllerClubCategoryService;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.service.port.ClubCategoryServiceClubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubCategoryServiceImpl implements ClubControllerClubCategoryService {
	private final ClubCategoryServiceClubCategoryRepository clubCategoryRepository;

	public ClubCategory findById(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}
}

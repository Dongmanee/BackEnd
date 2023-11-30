package com.dongmanee.domain.club.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.controller.port.ClubControllerClubCategoryService;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.service.port.ClubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubControllerClubCategoryServiceImpl implements ClubControllerClubCategoryService {
	private final ClubCategoryRepository clubCategoryRepository;

	public ClubCategory findById(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}
}

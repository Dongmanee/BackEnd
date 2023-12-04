package com.dongmanee.domain.club.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.service.port.ClubCategoryServiceClubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubCategoryServiceImpl implements ClubCategoryService {
	private final ClubCategoryServiceClubCategoryRepository clubCategoryRepository;

	public ClubCategory findById(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}
}

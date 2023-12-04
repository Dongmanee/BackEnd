package com.dongmanee.domain.club.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.dao.ClubCategoryRepository;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubCategoryServiceImpl implements ClubCategoryService {
	private final ClubCategoryRepository clubCategoryRepository;

	public ClubCategory findById(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}
}

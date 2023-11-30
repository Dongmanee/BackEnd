package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.service.port.ClubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Named("categoryMapper")
public class CategoryMapper {
	private final ClubCategoryRepository clubCategoryRepository;

	@Named("categoryIdToEntity")
	public ClubCategory categoryConverter(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}
}

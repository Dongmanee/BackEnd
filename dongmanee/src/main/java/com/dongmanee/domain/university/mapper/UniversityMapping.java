package com.dongmanee.domain.university.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.dongmanee.domain.university.dao.UniversityRepository;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.exception.UniversityIdExistsException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Named("universityMapper")
public class UniversityMapping {
	private final UniversityRepository universityRepository;

	@Named("universityIdToUniversityEntity")
	public University universityIdToUniversityEntity(Long universityId) {
		return universityRepository.findById(universityId).orElseThrow(() -> new UniversityIdExistsException());
	}
}

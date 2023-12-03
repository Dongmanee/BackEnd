package com.dongmanee.domain.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.service.port.UniversityServiceUniversityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
	private final UniversityServiceUniversityRepository universityRepository;

	@Override
	public List<University> findAll() {
		return universityRepository.findAll();
	}

	@Override
	public Optional<University> findById(Long id) {
		return universityRepository.findById(id);
	}
}

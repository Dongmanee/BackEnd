package com.dongmanee.domain.university.service;

import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.university.domain.University;

public interface UniversityService {
	List<University> findAll();

	Optional<University> findById(Long id);
}

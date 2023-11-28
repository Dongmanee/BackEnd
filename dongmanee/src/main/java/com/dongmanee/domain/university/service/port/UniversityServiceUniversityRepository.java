package com.dongmanee.domain.university.service.port;

import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.university.domain.University;

public interface UniversityServiceUniversityRepository {

	List<University> findAll();

	Optional<University> findById(Long id);
}

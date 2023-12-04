package com.dongmanee.domain.university.dao;

import java.util.List;
import java.util.Optional;

import com.dongmanee.domain.university.domain.University;

public interface UniversityRepository {

	List<University> findAll();

	Optional<University> findById(Long id);
}

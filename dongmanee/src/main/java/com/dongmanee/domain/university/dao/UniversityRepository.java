package com.dongmanee.domain.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.service.port.UniversityServiceUniversityRepository;

public interface UniversityRepository extends JpaRepository<University, Long>, UniversityServiceUniversityRepository {
}

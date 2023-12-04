package com.dongmanee.domain.university.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.university.dao.UniversityRepository;
import com.dongmanee.domain.university.domain.University;

public interface UniversityJpaRepository extends JpaRepository<University, Long>, UniversityRepository {
}

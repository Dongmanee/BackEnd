package com.dongmanee.domain.club.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.dao.ClubCategoryRepository;
import com.dongmanee.domain.club.domain.ClubCategory;

public interface ClubCategoryJpaRepository extends JpaRepository<ClubCategory, Long>,
	ClubCategoryRepository {
}

package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.service.port.ClubCategoryServiceClubCategoryRepository;

public interface ClubCategoryDataRepository extends JpaRepository<ClubCategory, Long>,
	ClubCategoryServiceClubCategoryRepository {
}

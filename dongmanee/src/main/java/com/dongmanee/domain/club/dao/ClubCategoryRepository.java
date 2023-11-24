package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.ClubCategory;

public interface ClubCategoryRepository
	extends JpaRepository<ClubCategory, Long> {
}

package com.dongmanee.domain.club.service.port;

import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubCategory;

public interface ClubCategoryRepository {
	Optional<ClubCategory> findById(Long id);
}

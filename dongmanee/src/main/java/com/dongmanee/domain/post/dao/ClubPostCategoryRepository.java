package com.dongmanee.domain.post.dao;

import java.util.Optional;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.post.domain.ClubPostCategory;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

public interface ClubPostCategoryRepository {
	Optional<ClubPostCategory> findByNameAndClub(ClubPostCategoryDetails name, Club club);
}

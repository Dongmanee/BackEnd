package com.dongmanee.domain.post.dao;

import java.util.Optional;

import com.dongmanee.domain.post.domain.ClubPostCategory;

public interface ClubPostCategoryRepository {
	Optional<ClubPostCategory> findByName(String name);
}

package com.dongmanee.domain.post.dao.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.ClubPostCategoryRepository;
import com.dongmanee.domain.post.domain.ClubPostCategory;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

public interface ClubPostCategoryJpaRepository
	extends JpaRepository<ClubPostCategory, Long>, ClubPostCategoryRepository {
	Optional<ClubPostCategory> findByName(ClubPostCategoryDetails name);
}

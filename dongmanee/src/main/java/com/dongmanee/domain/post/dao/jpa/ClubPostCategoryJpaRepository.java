package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.ClubPostCategoryRepository;
import com.dongmanee.domain.post.domain.ClubPostCategory;

public interface ClubPostCategoryJpaRepository
	extends JpaRepository<ClubPostCategory, Long>, ClubPostCategoryRepository {
}

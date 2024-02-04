package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.PostCategoryRepository;
import com.dongmanee.domain.post.domain.ClubPostCategory;

public interface PostCategoryJpaRepository extends JpaRepository<ClubPostCategory, Long>, PostCategoryRepository {
}

package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.PostCategoryRepository;
import com.dongmanee.domain.post.domain.PostCategory;

public interface PostCategoryJpaRepository extends JpaRepository<PostCategory, Long>, PostCategoryRepository {
}

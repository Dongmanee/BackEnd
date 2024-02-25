package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.PostLikeRepository;
import com.dongmanee.domain.post.domain.Like;

public interface PostLikeJpaRepository extends JpaRepository<Like, Long>, PostLikeRepository {
}

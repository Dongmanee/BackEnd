package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.LikeRepository;
import com.dongmanee.domain.post.domain.Like;

public interface LikeJpaRepository extends JpaRepository<Like, Long>, LikeRepository {
}

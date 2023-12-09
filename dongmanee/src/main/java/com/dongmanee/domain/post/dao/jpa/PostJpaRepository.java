package com.dongmanee.domain.post.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.post.dao.PostRepository;
import com.dongmanee.domain.post.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostRepository {
}

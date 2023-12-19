package com.dongmanee.domain.post.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dongmanee.domain.post.domain.Post;

public interface PostRepository {

	List<Post> findEveryPostsAfterCursor(Long clubId, LocalDateTime cursor, Pageable pageable);

	List<Post> findSpecificPostsAfterCursor(Long clubId, String category, LocalDateTime cursor, Pageable pageable);

	List<Post> findWithoutSpecificPostsAfterCursor(Long clubId, LocalDateTime cursor, Pageable pageable);

	List<Post> findLatestPostByClubId(Long clubId, Pageable pageable);
}

package com.dongmanee.domain.post.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dongmanee.domain.post.domain.ClubPost;

public interface ClubPostRepository {

	List<ClubPost> findEveryPostsAfterCursor(Long clubId, Long cursor, Pageable pageable);

	List<ClubPost> findSpecificPostsAfterCursor(Long clubId, String category, Long cursor, Pageable pageable);

	List<ClubPost> findWithoutSpecificPostsAfterCursor(Long clubId, Long cursor, Pageable pageable);

	List<ClubPost> findAnnouncementPostByClubId(Long clubId, Pageable pageable);
}

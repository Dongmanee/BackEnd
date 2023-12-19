package com.dongmanee.domain.post.dao.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongmanee.domain.post.dao.PostRepository;
import com.dongmanee.domain.post.domain.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostRepository {

	@Query("SELECT p FROM Post p LEFT JOIN FETCH p.club LEFT JOIN FETCH p.category LEFT JOIN FETCH p.member WHERE p.club.id = :clubId AND p.createdAt > :cursor ORDER BY p.createdAt ASC")
	List<Post> findEveryPostsAfterCursor(@Param("clubId") Long clubId, LocalDateTime cursor, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.category.name = :category AND p.club.id = :clubId AND p.createdAt > :cursor ORDER BY p.createdAt ASC")
	List<Post> findSpecificPostsAfterCursor(@Param("clubId") Long clubId, @Param("category") String category,
		LocalDateTime cursor,
		Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.category.name != '공지사항' AND p.category.name != '문의사항' AND p.club.id = :clubId AND p.createdAt > :cursor ORDER BY p.createdAt ASC")
	List<Post> findWithoutSpecificPostsAfterCursor(@Param("clubId") Long clubId, LocalDateTime cursor,
		Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.club.id = :clubId ORDER BY p.createdAt DESC")
	Page<Post> findPostsByClubId(Long clubId, Pageable pageable);

	default List<Post> findLatestPostByClubId(Long clubId, Pageable pageable) {
		return findPostsByClubId(clubId, pageable)
			.stream()
			.toList();
	}
}

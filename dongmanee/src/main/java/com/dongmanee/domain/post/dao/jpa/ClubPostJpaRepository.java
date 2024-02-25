package com.dongmanee.domain.post.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongmanee.domain.post.dao.ClubPostRepository;
import com.dongmanee.domain.post.domain.ClubPost;

public interface ClubPostJpaRepository extends JpaRepository<ClubPost, Long>, ClubPostRepository {

	@Query(
		"SELECT DISTINCT p FROM ClubPost p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.category.club LEFT JOIN FETCH p.member "
			+ "WHERE p.category.club.id = :clubId AND (:cursor IS NULL OR p.id < :cursor) ORDER BY p.createdAt DESC")
	List<ClubPost> findEveryPostsAfterCursor(@Param("clubId") Long clubId, @Param("cursor") Long cursor,
		Pageable pageable);

	@Query("SELECT p FROM ClubPost p "
		+ "LEFT JOIN FETCH p.category LEFT JOIN FETCH p.category.club  LEFT JOIN FETCH p.member "
		+ "WHERE p.category.name = :category AND p.category.club.id = :clubId "
		+ "AND (:cursor IS NULL OR p.id < :cursor) "
		+ "ORDER BY p.createdAt DESC")
	List<ClubPost> findSpecificPostsAfterCursor(@Param("clubId") Long clubId, @Param("category") String category,
		@Param("cursor") Long cursor, Pageable pageable);

	@Query("SELECT DISTINCT p FROM ClubPost p "
		+ "LEFT JOIN FETCH p.category LEFT JOIN FETCH p.category.club LEFT JOIN FETCH p.member "
		+ "WHERE p.category.name != '공지사항' AND p.category.name != '문의사항' AND "
		+ "p.category.club.id = :clubId AND (:cursor IS NULL OR p.id < :cursor) "
		+ "ORDER BY p.createdAt DESC")
	List<ClubPost> findWithoutSpecificPostsAfterCursor(@Param("clubId") Long clubId, Long cursor,
		Pageable pageable);

	@Query("SELECT DISTINCT p FROM ClubPost p "
		+ "LEFT JOIN FETCH p.category LEFT JOIN FETCH p.category.club LEFT JOIN FETCH p.member "
		+ "WHERE p.category.club.id = :clubId AND p.category.name = '공지사항' ORDER BY p.createdAt DESC")
	Page<ClubPost> findAnnouncementPostsByClubId(Long clubId, Pageable pageable);

	default List<ClubPost> findAnnouncementPostByClubId(Long clubId, Pageable pageable) {
		return findAnnouncementPostsByClubId(clubId, pageable)
			.stream()
			.toList();
	}
}

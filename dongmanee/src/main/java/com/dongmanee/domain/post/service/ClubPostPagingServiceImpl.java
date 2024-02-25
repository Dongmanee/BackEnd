package com.dongmanee.domain.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.dto.request.PostSearchingInfo;
import com.dongmanee.domain.post.dao.ClubPostRepository;
import com.dongmanee.domain.post.domain.ClubPost;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubPostPagingServiceImpl implements ClubPostPagingService {

	private final ClubPostRepository postRepository;

	public List<ClubPost> pagingDivider(PostSearchingInfo request) {
		switch (request.getPostCategory()) {
			case MAIN_PAGE -> {
				return findAnnouncement(request.getClubId());
			}
			case ALL -> {
				return findPosts(request.getClubId(), request.getCursor(), request.getPageSize());
			}
			case ANNOUNCEMENT, QUESTION -> {
				return findPostsWithCategory(request.getClubId(), request.getCursor(), request.getPageSize(),
					request.getPostCategory());
			}
			case FREE -> {
				return findFreeCategoryPosts(request.getClubId(), request.getCursor(), request.getPageSize());
			}
		}
		return new ArrayList<>();
	}

	private List<ClubPost> findAnnouncement(Long clubId) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		return postRepository.findAnnouncementPostByClubId(clubId, pageable);
	}

	private List<ClubPost> findPosts(Long clubId, Long cursor, Integer pageSize) {
		Pageable pageable = PageRequest.of(0, pageSize);
		return postRepository.findEveryPostsAfterCursor(clubId, cursor, pageable);
	}

	private List<ClubPost> findPostsWithCategory(Long clubId, Long cursor, Integer pageSize,
		ClubPostCategoryDetails postCategory) {
		Pageable pageable = PageRequest.of(0, pageSize);
		return postRepository.findSpecificPostsAfterCursor(clubId, postCategory.getValue(), cursor, pageable);
	}

	private List<ClubPost> findFreeCategoryPosts(Long clubId, Long cursor, Integer pageSize) {
		Pageable pageable = PageRequest.of(0, pageSize);
		return postRepository.findWithoutSpecificPostsAfterCursor(clubId, cursor, pageable);
	}

}

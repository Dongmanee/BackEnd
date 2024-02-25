package com.dongmanee.domain.post.service;

import org.springframework.stereotype.Service;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.post.dao.ClubPostCategoryRepository;
import com.dongmanee.domain.post.dao.ClubPostRepository;
import com.dongmanee.domain.post.domain.ClubPost;
import com.dongmanee.domain.post.domain.ClubPostCategory;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;
import com.dongmanee.domain.post.exception.ClubPostCategoryNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubPostServiceImpl implements ClubPostService {
	private final ClubPostRepository clubPostRepository;
	private final ClubPostCategoryRepository clubPostCategoryRepository;

	@Override
	public Long createClubPost(Member member, ClubPost inCompleteClubPostEntity, ClubPostCategory clubPostEntity) {
		ClubPost toBeSavedClubPostEntity = ClubPost.builder()
			.category(clubPostEntity)
			.member(member)
			.title(inCompleteClubPostEntity.getTitle())
			.body(inCompleteClubPostEntity.getBody())
			.isDeleted(Boolean.FALSE)
			.build();

		ClubPost savedClubPostEntity = clubPostRepository.save(toBeSavedClubPostEntity);

		return savedClubPostEntity.getId();
	}

	@Override
	public ClubPostCategory findClubPostCategoryByNamefindClubPostCategoryByNameAndClub(
		ClubPostCategoryDetails clubPostCategoryDetails, Club club) {
		return clubPostCategoryRepository.findByNameAndClub(clubPostCategoryDetails, club)
			.orElseThrow(ClubPostCategoryNotFoundException::new);
	}
}

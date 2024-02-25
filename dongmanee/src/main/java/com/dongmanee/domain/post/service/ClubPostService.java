package com.dongmanee.domain.post.service;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.post.domain.ClubPost;
import com.dongmanee.domain.post.domain.ClubPostCategory;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

public interface ClubPostService {
	Long createClubPost(Member member, ClubPost inCompleteClubPostEntity, ClubPostCategory clubPostEntity);

	ClubPostCategory findClubPostCategoryByNamefindClubPostCategoryByNameAndClub(
		ClubPostCategoryDetails clubPostCategoryDetails, Club club);
}

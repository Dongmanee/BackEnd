package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.request.CreateClubPostRequest;
import com.dongmanee.domain.post.domain.ClubPost;
import com.dongmanee.domain.post.domain.ClubPostCategory;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {})
public interface ClubPostMapper {
	@Mapping(source = "postTitle", target = "title")
	@Mapping(source = "postContent", target = "body")
		// @Mapping(source = "clubPostCategory", target = "category", qualifiedByName = "postCategoryToClubPostCategoryConverter")
	ClubPost toEntity(CreateClubPostRequest request);

	ClubPostCategory toClubPostCategoryEntity(ClubPostCategoryDetails postCategory, Club club);

	// @Named("postCategoryToClubPostCategoryConverter")
	// default ClubPostCategory postCategoryToClubPostCategory(ClubPostCategoryDetails clubPostCategoryDetails,
	// 	@Context ClubPostCategoryRepository clubPostCategoryRepository) {
	// 	return clubPostCategoryRepository.findByName(clubPostCategoryDetails)
	// 		.orElseThrow(ClubPostCategoryNotFoundException::new);
	// }

}

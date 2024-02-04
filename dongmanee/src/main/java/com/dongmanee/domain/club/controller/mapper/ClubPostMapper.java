package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {})
public interface ClubPostMapper {
	// ClubPost toEntity(CreateClubPostRequest request);
	//
	// ClubPostCategory toClubPostCategoryEntity(ClubPostCategoryDetails postCategory);
	//
	// @Named("postCategoryToClubPostCategory")
	// default ClubPostCategory postCategoryToClubPostCategory(ClubPostCategoryDetails postCategory,
	// 	@Context ClubPostCategoryRepository postCategoryRepository) {
	// 	Optional<ClubPostCategory> postCategoryDetails = postCategoryRepository.findByName(
	// 		postCategory.getValue());
	// }

}

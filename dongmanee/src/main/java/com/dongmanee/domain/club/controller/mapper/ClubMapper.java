package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.service.ClubCategoryService;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {ClubCategoryService.class})
public interface ClubMapper {
	@Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryConverter")
	Club toEntity(RequestCreateClub requestCreateClub,
		@Context ClubCategoryService clubCategoryService);

	Club toEntity(Long id, RequestEditClubDescriptionAndAddress dto);

	@Named("categoryConverter")
	default ClubCategory categoryConverter(Long categoryId, @Context ClubCategoryService clubCategoryService) {
		return clubCategoryService.findById(categoryId);
	}
}

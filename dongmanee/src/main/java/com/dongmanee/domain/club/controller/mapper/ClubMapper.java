package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.controller.port.ClubControllerClubCategoryService;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.service.port.ClubCategoryRepository;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {ClubControllerClubCategoryService.class})
public interface ClubMapper {
	@Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryConverter")
	Club toEntity(RequestCreateClub requestCreateClub,
		@Context ClubControllerClubCategoryService clubControllerClubCategoryService);

	@Named("categoryConverter")
	default ClubCategory categoryConverter(Long categoryId, @Context ClubControllerClubCategoryService clubCategoryService) {
		return clubCategoryService.findById(categoryId);
	}

	Club toEntity(Long id, RequestEditClubDescriptionAndAddress dto);
}

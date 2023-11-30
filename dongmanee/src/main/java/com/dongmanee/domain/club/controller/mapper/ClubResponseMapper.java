package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.controller.port.ClubControllerClubCategoryService;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.dto.response.ClubEditResponse;
import com.dongmanee.domain.club.dto.response.ClubSnsResponseDto;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {})
public interface ClubResponseMapper {

	@Mapping(source = "category", target = "categoryName", qualifiedByName = "categoryConverter")
	ClubEditResponse toDto(Club club);

	ClubSnsResponseDto toDto(ClubSns clubSns);

	@Named("categoryConverter")
	default String categoryConverter(ClubCategory category) {
		return category.getName();
	}

}

package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {CategoryMapper.class})
public interface ClubMapper {
	@Mapping(source = "categoryId", target = "category", qualifiedByName = {"categoryMapper", "categoryIdToEntity"})
	Club toEntity(RequestCreateClub requestCreateClub);

	Club toEntity(Long id, RequestEditClubDescriptionAndAddress dto);
}

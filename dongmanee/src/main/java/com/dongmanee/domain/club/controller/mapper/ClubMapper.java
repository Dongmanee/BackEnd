package com.dongmanee.domain.club.controller.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.dto.response.MemberJoinedClubResponseDto;
import com.dongmanee.domain.club.service.ClubService;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {ClubService.class})
public interface ClubMapper {
	@Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryConverter")
	Club toEntity(RequestCreateClub requestCreateClub,
		@Context ClubService clubService);

	Club toEntity(Long id, RequestEditClubDescriptionAndAddress dto);

	List<MemberJoinedClubResponseDto> toMemberJoinedClubResponseDto(List<Club> clubs);

	@Named("categoryConverter")
	default ClubCategory categoryConverter(Long categoryId, @Context ClubService clubService) {
		return clubService.findById(categoryId);
	}
}

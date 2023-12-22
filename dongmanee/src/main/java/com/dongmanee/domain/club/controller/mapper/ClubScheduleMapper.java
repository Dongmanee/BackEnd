package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.service.ClubService;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {ClubService.class})
public interface ClubScheduleMapper {
	@Mapping(source = "clubId", target = "club", qualifiedByName = "clubIdToClub")
	ClubSchedule toEntity(Long clubId, RequestCreateClubSchedule request, @Context ClubService clubService);

	@Named("clubIdToClub")
	default Club clubIdToClub(Long clubId, @Context ClubService clubService) {
		return clubService.findById(clubId);
	}
}

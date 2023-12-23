package com.dongmanee.domain.club.controller.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestCreateClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.dto.response.ResponseClubSchedule;
import com.dongmanee.domain.club.service.ClubService;
import com.dongmanee.global.jackson.mapper.JsonNullableMapper;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {ClubService.class, JsonNullableMapper.class})
public interface ClubScheduleMapper {
	@Mapping(source = "clubId", target = "club", qualifiedByName = "clubIdToClub")
	ClubSchedule toEntity(Long clubId, RequestCreateClubSchedule request, @Context ClubService clubService);

	@Mapping(source = "clubId", target = "club", qualifiedByName = "clubIdToClub")
	ClubSchedule toEntity(Long clubId, RequestUpdateClubSchedule request, @Context ClubService clubService);

	ResponseClubSchedule toResponseClubSchedule(ClubSchedule clubSchedule);

	@Named("clubIdToClub")
	default Club clubIdToClub(Long clubId, @Context ClubService clubService) {
		return clubService.findById(clubId);
	}
}

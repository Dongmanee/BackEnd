package com.dongmanee.domain.club.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.dto.request.RequestSns;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {})
public interface ClubSnsMapper {
	ClubSns toEntity(RequestSns requestSns);

	List<ClubSns> toEntity(List<RequestSns> clubSns);
}

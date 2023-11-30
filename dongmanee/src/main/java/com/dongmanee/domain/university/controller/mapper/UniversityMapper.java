package com.dongmanee.domain.university.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UniversityMapper {
	ResponseUniversity toResponseUniversity(University university);
}
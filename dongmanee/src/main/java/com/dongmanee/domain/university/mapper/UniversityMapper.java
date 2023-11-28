package com.dongmanee.domain.university.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.member.mapper.PasswordMapper;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.dto.response.ResponseUniversity;

@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {PasswordMapper.class, UniversityMapper.class})
public interface UniversityMapper {
	public ResponseUniversity toResponseUniversity(University university);
}
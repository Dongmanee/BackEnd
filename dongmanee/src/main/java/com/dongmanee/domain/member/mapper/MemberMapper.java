package com.dongmanee.domain.member.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.domain.university.exception.UniversityNotFoundException;
import com.dongmanee.domain.university.service.UniversityService;

//StudentMapper.java
//@Mapper를 이용하여 mapper임을 알려주고, MapStruct를 이용해 코드를 generate해야 함을 알려준다.
//componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다.
//unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
@Mapper(componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {PasswordMapper.class, UniversityService.class})
public interface MemberMapper {
	@Mapping(source = "password", target = "password", qualifiedByName = {"passwordEncoder", "passwordEncoded"})
	@Mapping(source = "universityId", target = "university")
	public Member toEntity(RequestSignup requestSignup, @Context UniversityService universityService);

	default University map(Long id, @Context UniversityService universityService) {
		return id != null ? universityService.findById(id).orElseThrow(UniversityNotFoundException::new) : null;
	}
}
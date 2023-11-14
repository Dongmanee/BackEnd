package com.dongmanee.domain.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.dto.request.RequestSignup;

//StudentMapper.java
//@Mapper를 이용하여 mapper임을 알려주고, MapStruct를 이용해 코드를 generate해야 함을 알려준다.
//componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다.
//unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

	// mapStruct가 코드를 만들 메소드를 선언해준다.
	// 아래 메소드는 Student 클래스를 받아, StudentDTO에 매핑하여 리턴하는 함수이다.
	public RequestSignup toDTO(Member member);

	public Member toEntity(RequestSignup requestSignup);
}
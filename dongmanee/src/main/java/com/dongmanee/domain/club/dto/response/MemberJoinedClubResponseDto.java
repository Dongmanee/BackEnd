package com.dongmanee.domain.club.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinedClubResponseDto {
	private String name;
	private String clubMainImageUrl;
}

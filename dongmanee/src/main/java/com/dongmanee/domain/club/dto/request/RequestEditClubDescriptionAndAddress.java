package com.dongmanee.domain.club.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class RequestEditClubDescriptionAndAddress {
	@Schema(description = "수정할 클럽의 클럽 설명", example = "String type edit Club Info")
	private String description;
	@Schema(description = "수정할 클럽의 주소", example = "String type edit Club address")
	private String address;
}

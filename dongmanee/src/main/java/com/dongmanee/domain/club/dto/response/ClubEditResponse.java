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
public class ClubEditResponse {
	private String categoryName;
	private String applicationId;
	private String name;
	private String description;
	private String clubMainImageUrl;
	private String clubBackgroundImageUrl;
	private String address;
	private Boolean isDeleted;
}

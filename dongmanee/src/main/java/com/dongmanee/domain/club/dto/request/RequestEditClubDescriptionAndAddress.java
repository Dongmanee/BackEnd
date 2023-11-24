package com.dongmanee.domain.club.dto.request;

import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private String description;
	@NotNull
	private String address;
}
